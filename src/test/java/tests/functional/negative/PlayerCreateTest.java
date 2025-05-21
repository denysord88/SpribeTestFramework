package tests.functional.negative;

import controllers.PlayerCreateController;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import testng.TestDataProviders;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class PlayerCreateTest extends BaseTest {
    @Test(enabled = true, groups = {"functional", "negative"}, description = "GET /player/create/{editor}?age={age}" +
            "&gender={gender}&login={login}&password={password}&role={role}&screenName={screenName}",
            dataProvider = "createPlayersNegative", dataProviderClass = TestDataProviders.class)
    public void createNegative(String TCNumber, String editor, String age, String gender, String login,
                               String password, String role, String screenName, int expectedStatusCode, String description) {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        String editorLogin = editor.equals("supervisor") ? SUPERVISOR_LOGIN :
                TEST_DATA.get(TCNumber) == null || TEST_DATA.get(TCNumber).get(editor) == null ? editor :
                        TEST_DATA.get(TCNumber).get(editor).getLogin();
        ValidatableResponse createdPlayer = new PlayerCreateController().getPlayerCreateResponse(
                editorLogin,
                age,
                gender,
                login + uniquePart,
                password,
                role,
                screenName + uniquePart);
        assertEquals(createdPlayer.extract().statusCode(), expectedStatusCode, "Response code is not like expected" +
                " for the negative test case: " + description + "\n" + createdPlayer.extract().statusLine() + " - " +
                createdPlayer.extract().body().asString());
    }
}
