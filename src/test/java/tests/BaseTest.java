package tests;

import controllers.PlayerGetController;
import testng.TestEventsListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.Date;

@Listeners({TestEventsListener.class})
public class BaseTest {
    protected static final String SUPERVISOR_LOGIN = new PlayerGetController().postPlayerGet(1L).getLogin();
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {}

    protected String getRandomString(String className, String methodName, long threadId) {
        return "denysord88_" + (className + "_" + methodName + "_" + new Date().getTime() + "_" + threadId).hashCode();
    }
}
