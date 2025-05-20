package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class PlayerCreateResponseDtoModel {
    private final Integer age;
    private final String gender;
    @NotNull
    private final long id;
    private final String login;
    private final String password;
    private final String role;
    private final String screenName;

    @JsonCreator
    public PlayerCreateResponseDtoModel(
            @JsonProperty(value = "age", required = true) Integer age,
            @JsonProperty(value = "gender", required = true) String gender,
            @JsonProperty(value = "id", required = true) long id,
            @JsonProperty(value = "login", required = true) String login,
            @JsonProperty(value = "password", required = true) String password,
            @JsonProperty(value = "role", required = true) String role,
            @JsonProperty(value = "screenName", required = true) String screenName) {
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }
}
