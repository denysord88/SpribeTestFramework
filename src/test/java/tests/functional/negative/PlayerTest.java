package tests.functional.negative;

import controllers.PlayerGetController;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class PlayerTest extends BaseTest {
    @Test(enabled = true, groups = {"functional", "negative"}, description = "POST /player/get")
    public void getNotExistedPlayer() {
        ValidatableResponse playerResponse = new PlayerGetController().postPlayerGetResponse(777L);
        assertEquals(playerResponse.extract().statusCode(), 404, "User shouldn't be found");
    }
}
