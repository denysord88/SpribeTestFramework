package tests.smoke;

import controllers.PlayerCreateController;
import controllers.PlayerDeleteController;
import controllers.PlayerGetController;
import controllers.PlayerUpdateController;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import models.PlayerGetByPlayerIdResponseDtoModel;
import models.PlayerUpdateResponseDtoModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertTrue;
import static testng.TestEventsListener.getCurrentTime;

public class PlayerUpdateTest extends BaseTest {
    private PlayerGetByPlayerIdResponseDtoModel createdPlayer = null;

    @BeforeMethod(alwaysRun = true)
    public void createPlayer() {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        PlayerCreateResponseDtoModel createdPlayerResponseModel = new PlayerCreateController().getPlayerCreate(
                SUPERVISOR_LOGIN,
                "18",
                "MALE",
                "testLoginToUpdate_" + uniquePart,
                "testPassword1",
                "USER",
                "testScreenName_" + uniquePart);
        System.out.println("[DEBUG] " + getCurrentTime() + " Player created with ID: " + createdPlayerResponseModel.getId());
        createdPlayer = new PlayerGetByPlayerIdResponseDtoModel(
                18,
                "MALE",
                createdPlayerResponseModel.getId(),
                "testLoginToUpdate_" + uniquePart,
                "testPassword1",
                "USER",
                "testScreenName_" + uniquePart);
    }

    @Description("Updating player")
    @Test(enabled = true, groups = {"smoke"}, description = "PATCH /player/get/{editor}/{id}")
    public void updatePlayer() {
        String newLogin = createdPlayer.getLogin().replace("Update_", "Update2_");
        String newScreenName = createdPlayer.getScreenName().replace("Name_", "Name2_");
        PlayerUpdateResponseDtoModel updatedPlayer = new PlayerUpdateController().patchPlayerUpdate(
                SUPERVISOR_LOGIN,
                createdPlayer.getId(),
                19,
                "FEMALE",
                newLogin,
                "testPassword2",
                "ADMIN",
                newScreenName
        );
        StringBuffer errors = new StringBuffer();
        if (updatedPlayer.getAge() == null || !updatedPlayer.getAge().equals(19))
            errors.append("\nAge '" + updatedPlayer.getAge() + "' is not equal to 19 from request. ");
        if (updatedPlayer.getGender() == null || !updatedPlayer.getGender().equals("FEMALE"))
            errors.append("\nGender '" + updatedPlayer.getGender() + "' is not equal to 'FEMALE' from request. ");
        if (updatedPlayer.getId() != createdPlayer.getId())
            errors.append("\nID '" + updatedPlayer.getId() + "' is not equal to '" + createdPlayer.getId() + "' of created user. ");
        if (updatedPlayer.getLogin() == null || !updatedPlayer.getLogin().equals(newLogin))
            errors.append("\nLogin '" + updatedPlayer.getLogin() + "' is not equal to '" + newLogin + "' from request. ");
        if (updatedPlayer.getRole() == null || !updatedPlayer.getRole().equals("ADMIN"))
            errors.append("\nRole '" + updatedPlayer.getRole() + "' is not equal to 'ADMIN' from request. " +
                    "Supervisor can't make admin from user ");
        if (updatedPlayer.getScreenName() == null || !updatedPlayer.getScreenName().equals(newScreenName))
            errors.append("\nScreenName '" + updatedPlayer.getScreenName() + "' is not equal to '" + newScreenName +
                    "' from request. ");
        PlayerGetByPlayerIdResponseDtoModel player = new PlayerGetController().postPlayerGet(createdPlayer.getId());
        if (player.getPassword() == null || !player.getPassword().equals("testPassword2"))
            errors.append("\nPassword '" + player.getPassword() + "' in getPlayerById response is not equal to" +
                    " 'testPassword2' from PATCH request. ");
        assertTrue(errors.isEmpty(), "\nResponse of PATCH request is not equal to values from request: " + errors + "\n");
    }

    @AfterMethod(alwaysRun = true)
    public void deleteCreatedPlayer() {
        if (createdPlayer != null) {
            ValidatableResponse response = new PlayerDeleteController().deletePlayerDeleteResponse(SUPERVISOR_LOGIN, createdPlayer.getId());
            if (response.extract().statusCode() != 204) {
                System.err.println("[ERROR] " + getCurrentTime() + " Player not deleted with ID: " + createdPlayer.getId());
            } else {
                System.out.println("[DEBUG] " + getCurrentTime() + " Player deleted with ID: " + createdPlayer.getId());
            }
        }
    }
}
