package tests.smoke;

import controllers.PlayerCreateController;
import controllers.PlayerDeleteController;
import controllers.PlayerGetController;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import models.PlayerGetByPlayerIdResponseDtoModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static testng.TestEventsListener.getCurrentTime;

public class PlayerGetTest extends BaseTest {
    private static PlayerGetByPlayerIdResponseDtoModel createdPlayer = null;

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
                "testLoginToGetById_" + uniquePart,
                "testPassword1",
                "USER",
                "testScreenName_" + uniquePart);
        System.out.println("[DEBUG] " + getCurrentTime() + " Player created with ID: " + createdPlayerResponseModel.getId());
        createdPlayer = new PlayerGetByPlayerIdResponseDtoModel(
                18,
                "MALE",
                createdPlayerResponseModel.getId(),
                "testLoginToGetById_" + uniquePart,
                "testPassword1",
                "USER",
                "testScreenName_" + uniquePart);
    }

    @Description("Checking player by ID")
    @Test(enabled = true, groups = {"smoke"}, description = "POST /player/get")
    public void getPlayer() {
        PlayerGetByPlayerIdResponseDtoModel player = new PlayerGetController().postPlayerGet(createdPlayer.getId());
        assertEquals(player, createdPlayer);
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
