package controllers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.PlayerGetByPlayerIdRequestDtoModel;
import models.PlayerGetByPlayerIdResponseDtoModel;

import static io.restassured.RestAssured.given;

public class PlayerGetController extends BaseController {
    @Step("RAW response of getting player by id={playerId}")
    public ValidatableResponse postPlayerGetResponse(long playerId) {
        return given(requestSpecification)
                .body(new PlayerGetByPlayerIdRequestDtoModel(playerId))
                .post("/get")
                .then();
    }

    @Step("Getting player by id={playerId}")
    public PlayerGetByPlayerIdResponseDtoModel postPlayerGet(long playerId) {
        ValidatableResponse response = postPlayerGetResponse(playerId);
        response.assertThat().statusCode(200);
        return response.extract().as(PlayerGetByPlayerIdResponseDtoModel.class);
    }
}
