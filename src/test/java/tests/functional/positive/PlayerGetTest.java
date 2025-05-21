package tests.functional.positive;

import controllers.PlayerGetController;
import models.PlayerCreateResponseDtoModel;
import models.PlayerGetByPlayerIdResponseDtoModel;
import org.testng.annotations.Test;
import testng.TestDataProviders;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PlayerGetTest extends BaseTest {
    @Test(enabled = true, groups = {"functional", "positive"}, description = "POST /player/get",
            dataProvider = "getPlayerPositive", dataProviderClass = TestDataProviders.class)
    public void getPlayer(String TCNumber, String playerAlias, String age, String gender, String login,
                          String password, String role, String screenName, String description) {
        PlayerCreateResponseDtoModel testPlayer = TEST_DATA.get(TCNumber).get(playerAlias);
        PlayerGetByPlayerIdResponseDtoModel player = new PlayerGetController().postPlayerGet(testPlayer.getId());

        assertEquals(player.getAge(), Integer.parseInt(age));
        assertEquals(player.getGender(), gender);
        assertEquals(player.getId(), testPlayer.getId());
        assertEquals(player.getLogin(), testPlayer.getLogin());
        assertEquals(player.getPassword(), password);
        assertEquals(player.getRole(), role);
        assertTrue(player.getScreenName().startsWith(screenName));
    }
}
