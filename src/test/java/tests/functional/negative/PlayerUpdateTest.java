package tests.functional.negative;

import controllers.PlayerCreateController;
import controllers.PlayerUpdateController;
import io.restassured.response.ValidatableResponse;
import models.PlayerUpdateResponseDtoModel;
import org.testng.annotations.Test;
import testng.TestDataProviders;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class PlayerUpdateTest extends BaseTest {
    @Test(enabled = true, groups = {"functional", "negative"}, description = "PATCH /player/get/{editor}/{id}",
            dataProvider = "updatePlayersNegative", dataProviderClass = TestDataProviders.class)
    public void updateNegative(String TCNumber, String playerAlias, String editor, String age, String gender,
                               String login, String password, String role, String screenName,
                               int expectedStatusCode, String description) {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        String editorLogin = editor.equals("supervisor") ? SUPERVISOR_LOGIN :
                TEST_DATA.get(TCNumber) == null || TEST_DATA.get(TCNumber).get(editor) == null ? editor :
                        TEST_DATA.get(TCNumber).get(editor).getLogin();
        ValidatableResponse updatedPlayer = new PlayerUpdateController().patchPlayerUpdateResponse(
                editorLogin,
                playerAlias.equals("supervisor") ? 1 : TEST_DATA.get(TCNumber).get(playerAlias).getId(),
                Integer.parseInt(age),
                gender,
                login + uniquePart,
                password,
                role,
                screenName + uniquePart);
        assertEquals(updatedPlayer.extract().statusCode(), expectedStatusCode, "Response code is not like expected" +
                " for the negative test case: " + description + "\n" + updatedPlayer.extract().statusLine() + " - " +
                updatedPlayer.extract().body().asString());
    }
}
