package tests;

import controllers.PlayerDeleteController;
import controllers.PlayerGetController;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import testng.TestEventsListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static testng.TestEventsListener.getCurrentTime;

@Listeners({TestEventsListener.class})
public class BaseTest {
    protected static final String SUPERVISOR_LOGIN = new PlayerGetController().postPlayerGet(1L).getLogin();
    public static final HashMap<String, HashMap<String, PlayerCreateResponseDtoModel>> TEST_DATA = new HashMap<>();
    @Description("BeforeSuite")
    @BeforeSuite(alwaysRun = true, description = "BeforeSuite")
    public void beforeSuite() {}

    protected String getRandomString(String className, String methodName, long threadId) {
        return "denysord88_" + (className + "_" + methodName + "_" + new Date().getTime() + "_" + threadId).hashCode();
    }

    @Description("AfterSuite")
    @AfterSuite(alwaysRun = true, description = "AfterSuite")
    public void afterSuite() {
        System.out.println("[DEBUG] " + getCurrentTime() + " AfterSuite started");
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
        System.out.println("[DEBUG] " + getCurrentTime() + " AfterSuite completed");
    }
}
