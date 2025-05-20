package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerDeleteRequestDtoModel {
    private final long playerId;

    @JsonCreator
    public PlayerDeleteRequestDtoModel(@JsonProperty(value = "playerId", required = true) long playerId) {
        this.playerId = playerId;
    }
}
