package tests.functional.positive;

import controllers.PlayerCreateController;
import controllers.PlayerDeleteController;
import io.qameta.allure.Description;
import models.PlayerCreateResponseDtoModel;
import org.testng.annotations.Test;
import testng.TestDataProviders;
import tests.BaseTest;

public class PlayerCreateTest extends BaseTest {
    @Test(enabled = true, groups = {"functional", "positive"}, description = "GET /player/create/{editor}?age={age}" +
            "&gender={gender}&login={login}&password={password}&role={role}&screenName={screenName}",
            dataProvider = "createPlayersPositive", dataProviderClass = TestDataProviders.class)
    public void createPlayersPositive(String TCNumber, String editor, String age, String gender, String login,
                                      String password, String role, String screenName, String description) {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        String editorLogin = editor.equals("supervisor") ? SUPERVISOR_LOGIN :
                TEST_DATA.get(TCNumber).get(editor).getLogin();
        PlayerCreateResponseDtoModel createdPlayer = new PlayerCreateController().getPlayerCreate(
                editorLogin,
                age,
                gender,
                login + uniquePart,
                password,
                role,
                screenName + uniquePart);
        new PlayerDeleteController().deletePlayerDeleteResponse(SUPERVISOR_LOGIN, createdPlayer.getId());
    }
}
