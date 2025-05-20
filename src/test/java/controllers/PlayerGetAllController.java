package controllers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.PlayerGetAllResponseDtoModel;

import static io.restassured.RestAssured.given;

public class PlayerGetAllController extends BaseController {
    @Step("RAW response of getting all players")
    public ValidatableResponse getPlayerGetAllResponse() {
        return given(requestSpecification)
                .get("/get/all")
                .then();
    }

    @Step("Getting all players")
    public PlayerGetAllResponseDtoModel getPlayerGetAll() {
        ValidatableResponse response = getPlayerGetAllResponse();
        response.assertThat().statusCode(200);
        return response.extract().as(PlayerGetAllResponseDtoModel.class);
    }
}
