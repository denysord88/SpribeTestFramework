package controllers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.PlayerUpdateRequestDtoModel;
import models.PlayerUpdateResponseDtoModel;

import static io.restassured.RestAssured.given;

public class PlayerUpdateController extends BaseController {
    @Step("RAW response of updating player by editor_login={editor} with id={id}, age={age}, gender={gender}, login={login}, password=***," +
            "role={role} and screenName={screenName}")
    public ValidatableResponse patchPlayerUpdateResponse(String editor, long id, int age, String gender, String login,
                                                         String password, String role, String screenName) {
        return given(requestSpecification)
                .body(new PlayerUpdateRequestDtoModel(age, gender, login, password, role, screenName))
                .patch("/update/" + editor + "/" + id)
                .then();
    }

    @Step("Updating player by editor_login={editor} with id={id}, age={age}, gender={gender}, login={login}, password=***," +
            "role={role} and screenName={screenName}")
    public PlayerUpdateResponseDtoModel patchPlayerUpdate(String editor, long id, int age, String gender, String login,
                                                          String password, String role, String screenName) {
        ValidatableResponse response = patchPlayerUpdateResponse(editor, id, age, gender, login, password, role, screenName);
        response.assertThat().statusCode(200);
        return response.extract().as(PlayerUpdateResponseDtoModel.class);
    }
}
