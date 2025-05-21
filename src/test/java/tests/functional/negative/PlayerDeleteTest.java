package tests.functional.negative;

import controllers.PlayerDeleteController;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;
import org.testng.annotations.Test;
import testng.TestDataProviders;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class PlayerDeleteTest extends BaseTest {
    @Test(enabled = true, groups = {"functional", "negative"}, description = "DELETE /player/delete/{editor}",
            dataProvider = "deletePlayersNegative", dataProviderClass = TestDataProviders.class)
    public void deleteNegative(String TCNumber, String playerAlias, String editor, int expectedStatusCode,
                               String description) {
        PlayerCreateResponseDtoModel testPlayer = TEST_DATA.get(TCNumber).get(playerAlias);
        String editorLogin = editor.equals("supervisor") ? SUPERVISOR_LOGIN :
                TEST_DATA.get(TCNumber).get(editor) == null ? editor : TEST_DATA.get(TCNumber).get(editor).getLogin();
        ValidatableResponse response = new PlayerDeleteController().deletePlayerDeleteResponse(editorLogin, testPlayer.getId());
        assertEquals(response.extract().statusCode(), expectedStatusCode, "Response code is not like expected" +
                " for the negative test case: " + description + "\n" + response.extract().statusLine() + " - " +
                response.extract().body().asString());
    }
}
