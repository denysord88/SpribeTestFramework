package controllers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.PlayerDeleteRequestDtoModel;
import models.ResponseEntityModel;

import static io.restassured.RestAssured.given;

public class PlayerDeleteController extends BaseController {
    @Step("RAW response of deleting player with id={playerId} by editor_login={editor}")
    public ValidatableResponse deletePlayerDeleteResponse(String editor, long playerId) {
        return given(requestSpecification)
                .body(new PlayerDeleteRequestDtoModel(playerId))
                .delete("/delete/" + editor)
                .then();
    }

    @Step("Deleting player with id={playerId} by editor_login={editor}")
    public ResponseEntityModel deletePlayerDelete(String editor, long playerId) {
        ValidatableResponse response = deletePlayerDeleteResponse(editor, playerId);
        response.assertThat().statusCode(200);
        return response.extract().as(ResponseEntityModel.class);
    }
}
