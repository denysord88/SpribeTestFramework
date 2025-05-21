package tests.e2e;

import controllers.*;
import io.restassured.response.ValidatableResponse;
import jakarta.validation.constraints.NotNull;
import models.PlayerCreateResponseDtoModel;
import models.PlayerGetAllResponseDtoModel;
import models.PlayerGetByPlayerIdResponseDtoModel;
import models.PlayerUpdateResponseDtoModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static testng.TestEventsListener.getCurrentTime;

public class E2ETest extends BaseTest {
    private long createdPlayerId = 0;

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
        PlayerGetAllResponseDtoModel allPlayersResponse = new PlayerGetAllController().getPlayerGetAll();

        PlayerGetAllResponseDtoModel.PlayerItemModel createdPlayerItem = null;
        for (int i = 0; i < allPlayersResponse.getPlayers().size(); i++) {
            if (allPlayersResponse.getPlayers().get(i).getId() == createdPlayerId) {
                createdPlayerItem = allPlayersResponse.getPlayers().get(i);
                break;
            }
        }
        assertTrue(createdPlayerItem != null, "Created player not found in the list.");
        assertEquals(createdPlayerItem.getAge(), Integer.valueOf(18), "Wrong age");
        assertEquals(createdPlayerItem.getGender(), "MALE", "Wrong Gender");
        //assertEquals(createdPlayerItem.getRole(), "admin", "Wrong Role");
        assertEquals(createdPlayerItem.getScreenName(), "ScreenName_" + uniquePart, "Wrong ScreenName");

        // Update
        PlayerUpdateResponseDtoModel updatedPlayer = new PlayerUpdateController().patchPlayerUpdate(
                SUPERVISOR_LOGIN,
                createdPlayerId,
                19,
                "FEMALE",
                "Login_updated_" + uniquePart,
                "testPassword2",
                "admin",
                "ScreenName_updated" + uniquePart
        );
        assertEquals(updatedPlayer.getAge(), Integer.valueOf(19), "Wrong age");
        assertEquals(updatedPlayer.getGender(), "FEMALE", "Wrong Gender");
        assertEquals(updatedPlayer.getLogin(), "Login_updated_" + uniquePart, "Wrong Login");
        assertEquals(new PlayerGetController().postPlayerGet(createdPlayerId).getPassword(),
                "testPassword2", "Wrong Password");
        assertEquals(updatedPlayer.getRole(), "admin", "Wrong Role");
        assertEquals(updatedPlayer.getScreenName(), "ScreenName_updated" + uniquePart, "Wrong ScreenName");

        // Delete
        ValidatableResponse response = new PlayerDeleteController().deletePlayerDeleteResponse(SUPERVISOR_LOGIN, createdPlayerId);
        assertEquals(response.extract().statusCode(), 204, "Failed to delete created player with ID: " +
                createdPlayerId + ". Status: " + response.extract().statusLine() + " - " + response.extract().body().asString());
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
