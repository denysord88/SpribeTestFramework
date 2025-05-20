package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerGetByPlayerIdRequestDtoModel {
    private final long playerId;

    @JsonCreator
    public PlayerGetByPlayerIdRequestDtoModel(@JsonProperty(value = "playerId", required = true) long playerId) {
        this.playerId = playerId;
    }
}
