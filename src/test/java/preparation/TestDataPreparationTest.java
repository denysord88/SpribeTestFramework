package preparation;

import controllers.PlayerCreateController;
import org.testng.annotations.Test;
import testng.TestDataProviders;
import tests.BaseTest;

import java.util.HashMap;

public class TestDataPreparationTest extends BaseTest {
    @Test(enabled = true, dataProvider = "testDataCreation", dataProviderClass = TestDataProviders.class,
            description = "GET /player/create/{editor}?age={age}&gender={gender}" +
                    "&login={login}&password={password}&role={role}&screenName={screenName}")
    public void prepareTestData(String TCNumber, String testPlayerAlias, String age, String gender,
                                String login, String password, String role, String screenName) throws InterruptedException {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        TEST_DATA.putIfAbsent(TCNumber, new HashMap<>());
        TEST_DATA.get(TCNumber).put(testPlayerAlias, new PlayerCreateController().getPlayerCreate(
                SUPERVISOR_LOGIN,
                age,
                gender,
                login + "_" + uniquePart,
                password,
                role,
                screenName + "_" + uniquePart
        ));
        System.out.println(TEST_DATA.get(TCNumber).get(testPlayerAlias).toString());
    }
}
