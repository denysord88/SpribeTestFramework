package tests.e2e;

import controllers.PlayerCreateController;
import controllers.PlayerDeleteController;
import controllers.PlayerGetController;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import models.PlayerGetByPlayerIdResponseDtoModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertTrue;
import static testng.TestEventsListener.getCurrentTime;

public class E2ETest extends BaseTest {
    private static long createdPlayerId = 0;
    @Test(enabled = true, groups = {"e2e"}, description = "Check all endpoints together")
    public void completeFlowTest() {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        // Create
        PlayerCreateResponseDtoModel createdPlayer = new PlayerCreateController().getPlayerCreate(
                SUPERVISOR_LOGIN,
                "18",
                "MALE",
                "Login_" + uniquePart,
                "123abcDEF098",
                "admin",
                "ScreenName_" + uniquePart
        );
        createdPlayerId = createdPlayer.getId();
        // Read
        PlayerGetByPlayerIdResponseDtoModel retrievedPlayer = new PlayerGetController().postPlayerGet(createdPlayerId);

        StringBuffer errors = new StringBuffer();
        if (retrievedPlayer.getId() != createdPlayer.getId())
            errors.append("\nID '" + retrievedPlayer.getId() + "' is not equal to '" + createdPlayerId +
                    "' of created user. ");
        if (retrievedPlayer.getLogin() == null || !retrievedPlayer.getLogin().equals("Login_" + uniquePart))
            errors.append("\nLogin '" + retrievedPlayer.getLogin() + "' is not equal to 'Login_" + uniquePart +
                    "' of created user. ");
        if (retrievedPlayer.getPassword() == null || !retrievedPlayer.getPassword().equals("123abcDEF098"))
            errors.append("\nPassword '" + retrievedPlayer.getPassword() +
                    "' is not equal to '123abcDEF098' of created user. ");
        if (retrievedPlayer.getScreenName() == null || !retrievedPlayer.getScreenName().equals("ScreenName_" + uniquePart))
            errors.append("\nScreenName '" + retrievedPlayer.getScreenName() + "' is not equal to 'ScreenName_" + uniquePart +
                    "' of created user. ");
        if (retrievedPlayer.getGender() == null || !retrievedPlayer.getGender().equals("MALE"))
            errors.append("\nGender '" + retrievedPlayer.getGender() + "' is not equal to 'MALE' of created user. ");
        if (retrievedPlayer.getAge() == null || !retrievedPlayer.getAge().equals(18))
            errors.append("\nAge '" + retrievedPlayer.getAge() + "' is not equal to 18 of created user. ");
        if (retrievedPlayer.getRole() == null || !retrievedPlayer.getRole().equals("admin"))
            errors.append("\nRole '" + retrievedPlayer.getRole() + "' is not equal to 'admin' of created user. ");
        assertTrue(errors.isEmpty(), "\nSome fields from getById response are not equal to created user: " +
                errors + "\n");

        // Read all


        // Update
        // Delete
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
