package tests.functional.positive;

import controllers.PlayerGetAllController;
import io.qameta.allure.Description;
import models.PlayerGetAllResponseDtoModel;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.HashSet;

import static org.testng.Assert.assertEquals;

// 4. Get All Players (GET /player/get/all)
public class PlayerGetAllTest extends BaseTest {
    @Description("PTC4.1 - Check that id, login and screenName are unique in the list")
    @Test(enabled = true, groups = {"functional", "positive"}, description = "GET /player/get/all")
    public void getAllPlayers() {
        PlayerGetAllResponseDtoModel allPlayersResponse = new PlayerGetAllController().getPlayerGetAll();
        HashSet<String> uniqueEntities = new HashSet<>();
        allPlayersResponse.getPlayers().forEach(player -> uniqueEntities.add("" + player.getId()));
        assertEquals(allPlayersResponse.getPlayers().size(), uniqueEntities.size(),
                "There are some duplicates by id in the list.");
        uniqueEntities.clear();
        allPlayersResponse.getPlayers().forEach(player -> uniqueEntities.add(player.getScreenName()));
        assertEquals(allPlayersResponse.getPlayers().size(), uniqueEntities.size(),
                "There are some duplicates by screenName in the list.");
    }
}
