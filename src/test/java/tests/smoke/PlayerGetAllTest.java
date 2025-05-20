package tests.smoke;

import controllers.PlayerGetAllController;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.BaseTest;

public class PlayerGetAllTest extends BaseTest {
    @Description("Validating response data format with all users in the list")
    @Test(enabled = true, groups = {"smoke"}, description = "GET /player/get/all")
    public void getAllPlayers() {
        new PlayerGetAllController().getPlayerGetAll();
    }
}
