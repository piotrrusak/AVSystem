package org.example.model.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddVehicle.class, name = "addVehicle"),
        @JsonSubTypes.Type(value = Step.class, name = "step")
})
public interface Command {
}
