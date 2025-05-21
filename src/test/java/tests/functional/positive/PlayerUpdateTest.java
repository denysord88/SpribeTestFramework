package tests.functional.positive;

import controllers.PlayerCreateController;
import controllers.PlayerDeleteController;
import controllers.PlayerUpdateController;
import models.PlayerCreateResponseDtoModel;
import models.PlayerUpdateResponseDtoModel;
import org.testng.annotations.Test;
import testng.TestDataProviders;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class PlayerUpdateTest extends BaseTest {
    @Test(enabled = true, groups = {"functional", "positive"}, description = "PATCH /player/get/{editor}/{id}",
            dataProvider = "updatePlayersPositive", dataProviderClass = TestDataProviders.class)
    public void updatePlayersPositive(String TCNumber, String playerAlias, String editor, String age, String gender, String login,
                                      String password, String role, String screenName, String description) {
        String uniquePart = getRandomString(
                this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                Thread.currentThread().getId());
        String editorLogin = editor.equals("supervisor") ? SUPERVISOR_LOGIN :
                TEST_DATA.get(TCNumber).get(editor).getLogin();
        PlayerUpdateResponseDtoModel updatedPlayer = new PlayerUpdateController().patchPlayerUpdate(
                editorLogin,
                TEST_DATA.get(TCNumber).get(playerAlias).getId(),
                Integer.parseInt(age),
                gender,
                login + uniquePart,
                password,
                role,
                screenName + uniquePart);
        assertEquals(updatedPlayer, new PlayerUpdateResponseDtoModel(
                Integer.parseInt(age),
                gender,
                TEST_DATA.get(TCNumber).get(playerAlias).getId(),
                login + uniquePart,
                role,
                screenName + uniquePart));
    }
}
