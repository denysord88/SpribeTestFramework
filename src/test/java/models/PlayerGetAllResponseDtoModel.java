package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class PlayerGetAllResponseDtoModel {
    private final List<PlayerItemModel> players;

    @JsonCreator
    public PlayerGetAllResponseDtoModel(@JsonProperty(value = "players", required = true) List<PlayerItemModel> players) {
        this.players = players;
    }

    @Data
    public static class PlayerItemModel {
        private final Integer age;
        private final String gender;
        @NotNull
        private final long id;
        private final String role;
        private final String screenName;

        @JsonCreator
        public PlayerItemModel(
                @JsonProperty(value = "age", required = true) Integer age,
                @JsonProperty(value = "gender", required = true) String gender,
                @JsonProperty(value = "id", required = true) long id,
                @JsonProperty(value = "role", required = false) String role,
                @JsonProperty(value = "screenName", required = true) String screenName) {
            this.age = age;
            this.gender = gender;
            this.id = id;
            this.role = role;
            this.screenName = screenName;
        }
    }
}
