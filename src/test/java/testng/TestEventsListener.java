package testng;

import controllers.PlayerDeleteController;
import controllers.PlayerGetAllController;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import models.PlayerGetAllResponseDtoModel;
import org.testng.*;
import org.testng.annotations.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static conf.Configuration.UNIQUE_FRAMEWORK_TEST_DATA_ID;
import static tests.BaseTest.SUPERVISOR_LOGIN;
import static tests.BaseTest.TEST_DATA;

public class TestEventsListener implements ITestListener, IMethodInterceptor {
    private static int totalCountOfTests = 0;
    private static final HashSet<String> runnedTests = new HashSet<>();

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        methods.sort(Comparator.comparingInt(p -> p.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).priority()));
        return methods;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String testPath = iTestResult.getTestClass().getName() + "." + iTestResult.getMethod().getMethodName() + "(...)";
        if (runnedTests.contains(testPath)) return;
        runnedTests.add(testPath);
        System.out.println("[INFO] " + getCurrentTime() + " Running test method " + runnedTests.size() + " from " + totalCountOfTests +
                "\t" + testPath);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        ((TestRunner) iTestContext).getTestClasses().forEach(testClass -> {
            Arrays.stream(testClass.getTestMethods()).forEach(iTestNGMethod -> {
                if (iTestNGMethod.getEnabled()) totalCountOfTests++;
            });
        });
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("[INFO] " + getCurrentTime() + " All tests completed");
        runnedTests.clear();
        totalCountOfTests = 0;
        if (iTestContext.getName().equals("MainTests")) {
            for (var entry : TEST_DATA.entrySet()) {
                String testCase = entry.getKey();
                Map<String, PlayerCreateResponseDtoModel> players = entry.getValue();
                for (var playerEntry : players.entrySet()) {
                    String alias = playerEntry.getKey();
                    PlayerCreateResponseDtoModel player = playerEntry.getValue();
                    long playerId = player.getId();

                    ValidatableResponse response = new PlayerDeleteController().deletePlayerDeleteResponse(SUPERVISOR_LOGIN, playerId);
                    if (response.extract().statusCode() != 204) {
                        System.err.println("[ERROR] " + getCurrentTime() + " Player not deleted [TC: " + testCase +
                                ", alias: " + alias + ", ID: " + playerId + "]");
                    } else {
                        System.out.println("[DEBUG] " + getCurrentTime() + " Player deleted [TC: " + testCase +
                                ", alias: " + alias + ", ID: " + playerId + "]");
                    }
                }
            }
            new PlayerGetAllController().getPlayerGetAll().getPlayers().forEach(playerItemModel -> {
                if (playerItemModel.getScreenName().contains(UNIQUE_FRAMEWORK_TEST_DATA_ID)) {
                    new PlayerDeleteController().deletePlayerDeleteResponse(SUPERVISOR_LOGIN, playerItemModel.getId());
                }
            });
        }

        if (iTestContext.getFailedConfigurations().getAllResults().size() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append("=====================================\n");
            iTestContext.getFailedConfigurations().getAllResults().forEach(result -> {
                sb.append("Configuration error in method ");
                sb.append(result.getInstanceName());
                sb.append(".");
                sb.append(result.getName());
                sb.append("()\nError is: ");
                sb.append(result.getThrowable().getMessage());
                sb.append("\n=====================================\n");
            });
            System.out.println(sb);
            throw new RuntimeException(sb.toString());
        }
        if (iTestContext.getFailedTests().size() > 0) {
            StringBuilder sb = new StringBuilder();
            prepareReportHeader(sb, iTestContext);
            addTestFailuresToTestReport(sb, iTestContext);
            System.out.println(sb);
            throw new RuntimeException(sb.toString());
        }
    }

    private void prepareReportHeader(StringBuilder reportString, ITestContext testCases) {
        reportString.append("\n\n===============================================\n");
        reportString.append(testCases.getSuite().getName());
        reportString.append("\nTotal tests: ");
        reportString.append(testCases.getPassedTests().size() +
                testCases.getFailedTests().size() +
                testCases.getSkippedTests().size());
        reportString.append(", Passes: ");
        reportString.append(testCases.getPassedTests().size());
        reportString.append(", Failures: ");
        reportString.append(testCases.getFailedTests().size());
        reportString.append(", Skips: ");
        reportString.append(testCases.getSkippedTests().size());
        reportString.append("\n===============================================\n\n");
    }

    private void addTestFailuresToTestReport(StringBuilder reportString, ITestContext testCases) {
        testCases.getFailedTests().getAllResults().forEach(iTestResult -> {
            reportString.append(">>> Test method failed: ")
                    .append(iTestResult.getTestClass().getName())
                    .append(".")
                    .append(iTestResult.getName())
                    .append("()\nFailed cause: ")
                    .append(iTestResult.getThrowable().toString()
                    );
            reportString.append("\n===========================================\n");
        });
    }

    public static String getCurrentTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        return now.format(formatter);
    }
}
