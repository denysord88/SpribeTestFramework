package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerUpdateRequestDtoModel {
    private final int age;
    private final String gender;
    private final String login;
    private final String password;
    private final String role;
    private final String screenName;

    @JsonCreator
    public PlayerUpdateRequestDtoModel(
            @JsonProperty(value = "age", required = true) int age,
            @JsonProperty(value = "gender", required = true) String gender,
            @JsonProperty(value = "login", required = true) String login,
            @JsonProperty(value = "password", required = true) String password,
            @JsonProperty(value = "role", required = true) String role,
            @JsonProperty(value = "screenName", required = true) String screenName) {
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }
}
