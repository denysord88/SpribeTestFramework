package tests.functional.positive;

import controllers.PlayerDeleteController;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import org.testng.annotations.Test;
import testng.TestDataProviders;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class PlayerDaleteTest extends BaseTest {
    @Test(enabled = true, groups = {"functional", "positive"}, description = "DELETE /player/delete/{editor}",
            dataProvider = "deletePlayersPositive", dataProviderClass = TestDataProviders.class)
    public void deletePlayersPositive(String TCNumber, String playerAlias, String editor, String description) {
        PlayerCreateResponseDtoModel testPlayer = TEST_DATA.get(TCNumber).get(playerAlias);
        String editorLogin = editor.equals("supervisor") ? SUPERVISOR_LOGIN :
                TEST_DATA.get(TCNumber).get(editor).getLogin();
        ValidatableResponse response = new PlayerDeleteController().deletePlayerDeleteResponse(editorLogin, testPlayer.getId());
        assertEquals(response.extract().statusLine(), "HTTP/1.1 204 ", "Failed to delete player " +
                playerAlias + " with ID: " + testPlayer.getId() + " by editor '" + editor + "': HTTP " +
                response.extract().statusLine() + " - " + response.extract().body().asString());
    }
}
