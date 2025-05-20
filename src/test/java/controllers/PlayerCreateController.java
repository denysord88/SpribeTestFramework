package controllers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.PlayerCreateResponseDtoModel;

import static io.restassured.RestAssured.given;

public class PlayerCreateController extends BaseController {
    @Step("RAW response of creating a new player with age={age}, gender={gender}, login={login}, password=***, " +
            "role={role}, screenName={screenName} by editor_login={editor}")
    public ValidatableResponse getPlayerCreateResponse(String editor, String age, String gender, String login,
                                                       String password, String role, String screenName) {
        return given(requestSpecification)
                .queryParam("age", age)
                .queryParam("gender", gender)
                .queryParam("login", login)
                .queryParam("password", password)
                .queryParam("role", role)
                .queryParam("screenName", screenName)
                .get("/create/" + editor)
                .then();
    }

    @Step("Creating a new player with age={age}, gender={gender}, login={login}, password=***, role={role}, " +
            "screenName={screenName} by editor_login={editor}")
    public PlayerCreateResponseDtoModel getPlayerCreate(String editor, String age, String gender, String login,
                                                        String password, String role, String screenName) {
        ValidatableResponse response = getPlayerCreateResponse(editor, age, gender, login, password, role, screenName);
        response.assertThat().statusCode(200);
        return response.extract().as(PlayerCreateResponseDtoModel.class);
    }
}
