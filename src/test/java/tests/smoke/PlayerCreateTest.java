package tests.smoke;

import controllers.PlayerCreateController;
import controllers.PlayerDeleteController;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertTrue;
import static testng.TestEventsListener.getCurrentTime;

public class PlayerCreateTest extends BaseTest {
    private long createdPlayerId = 0;

    @Description("Creating new player")
    @Test(enabled = true, groups = {"smoke"}, description = "GET /player/create/{editor}?age={age}&gender={gender}" +
            "&login={login}&password={password}&role={role}&screenName={screenName}")
    public void createPlayer() {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        PlayerCreateResponseDtoModel createdPlayer = new PlayerCreateController().getPlayerCreate(
                SUPERVISOR_LOGIN,
                "18",
                "MALE",
                "testLogin_" + uniquePart,
                "testPassword1",
                "USER",
                "testScreenName_" + uniquePart);
        System.out.println("[DEBUG] " + getCurrentTime() + " Player created with ID: " + createdPlayer.getId());
        createdPlayerId = createdPlayer.getId();
        StringBuffer errors = new StringBuffer();
        if (createdPlayer.getLogin() == null || !createdPlayer.getLogin().equals("testLogin_" + uniquePart))
            errors.append("\nLogin '" + createdPlayer.getLogin() + "' is not equal to 'testLogin_" + uniquePart +
                    "' from request. ");
        if (createdPlayer.getPassword() == null || !createdPlayer.getPassword().equals("testPassword1"))
            errors.append("\nPassword '" + createdPlayer.getPassword() + "' is not equal to 'testPassword1' from request. ");
        if (createdPlayer.getScreenName() == null || !createdPlayer.getScreenName().equals("testScreenName_" + uniquePart))
            errors.append("\nScreenName '" + createdPlayer.getScreenName() + "' is not equal to 'testScreenName_" +
                    uniquePart + "' from request. ");
        if (createdPlayer.getGender() == null || !createdPlayer.getGender().equals("MALE"))
            errors.append("\nGender '" + createdPlayer.getGender() + "' is not equal to 'MALE' from request. ");
        if (createdPlayer.getAge() == null || !createdPlayer.getAge().equals(18))
            errors.append("\nAge '" + createdPlayer.getAge() + "' is not equal to 18 from request. ");
        if (createdPlayer.getRole() == null || !createdPlayer.getRole().equals("USER"))
            errors.append("\nRole '" + createdPlayer.getRole() + "' is not equal to 'USER' from request. ");
        assertTrue(errors.isEmpty(), errors + "\n");
    }

    @AfterMethod(alwaysRun = true)
    public void deleteCreatedPlayer() {
        if (createdPlayerId != 0) {
            ValidatableResponse response = new PlayerDeleteController().deletePlayerDeleteResponse(SUPERVISOR_LOGIN, createdPlayerId);
            if (response.extract().statusCode() != 204) {
                System.err.println("[ERROR] " + getCurrentTime() + " Player not deleted with ID: " + createdPlayerId);
            } else {
                System.out.println("[DEBUG] " + getCurrentTime() + " Player deleted with ID: " + createdPlayerId);
            }
        }
    }
}
