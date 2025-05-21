package tests.smoke;

import controllers.PlayerCreateController;
import controllers.PlayerDeleteController;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static testng.TestEventsListener.getCurrentTime;

public class PlayerDeleteTest extends BaseTest {
    private long createdPlayerId = 0;

    @BeforeMethod(alwaysRun = true)
    public void createPlayer() {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        PlayerCreateResponseDtoModel createdPlayer = new PlayerCreateController().getPlayerCreate(
                SUPERVISOR_LOGIN,
                "18",
                "MALE",
                "testLoginToDelete_" + uniquePart,
                "testPassword1",
                "USER",
                "testScreenName_" + uniquePart);
        System.out.println("[DEBUG] " + getCurrentTime() + " Player created with ID: " + createdPlayer.getId());
        createdPlayerId = createdPlayer.getId();
    }

    @Description("Deleting existed player")
    @Test(enabled = true, groups = {"smoke"}, description = "DELETE /player/delete/{editor}")
    public void deletePlayer() {
        ValidatableResponse response = new PlayerDeleteController().deletePlayerDeleteResponse(SUPERVISOR_LOGIN, createdPlayerId);
        assertEquals(response.extract().statusCode(), 204, "Failed to delete created player with ID: " +
                createdPlayerId + ". Status: " + response.extract().statusLine() + " - " + response.extract().body().asString());
    }
}
