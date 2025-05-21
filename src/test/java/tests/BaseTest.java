package tests;

import controllers.PlayerDeleteController;
import controllers.PlayerGetAllController;
import controllers.PlayerGetController;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import models.PlayerGetAllResponseDtoModel;
import org.testng.annotations.AfterSuite;
import testng.TestEventsListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static conf.Configuration.UNIQUE_FRAMEWORK_TEST_DATA_ID;
import static testng.TestEventsListener.getCurrentTime;

@Listeners({TestEventsListener.class})
public class BaseTest {
    public static final String SUPERVISOR_LOGIN = new PlayerGetController().postPlayerGet(1L).getLogin();
    public static final HashMap<String, HashMap<String, PlayerCreateResponseDtoModel>> TEST_DATA = new HashMap<>();
    @Description("BeforeSuite")
    @BeforeSuite(alwaysRun = true, description = "BeforeSuite")
    public void beforeSuite() {
    }

    protected String getRandomString(String className, String methodName, long threadId) {
        return UNIQUE_FRAMEWORK_TEST_DATA_ID + "_" + (className + "_" + methodName + "_" + new Date().getTime() + "_" + threadId).hashCode();
    }

    @Description("AfterSuite")
    @AfterSuite(alwaysRun = true, description = "AfterSuite")
    public void afterSuite() {
    }
}
