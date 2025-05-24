package org.example.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.example.model.road.Direction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddVehicle implements Command {

    private String vehicleId;
    private String startRoad;
    private String endRoad;

    @JsonIgnore
    public Direction getStartRoadAsDirection() {
        return switch(this.startRoad) {
            case "north" -> Direction.NORTH;
            case "south" -> Direction.SOUTH;
            case "west" -> Direction.WEST;
            case "east" -> Direction.EAST;
            default -> throw new IllegalStateException("Unexpected value: " + this.startRoad);
        };
    }

    @JsonIgnore
    public Direction getEndRoadAsDirection() {
        return switch(this.endRoad) {
            case "north" -> Direction.NORTH;
            case "south" -> Direction.SOUTH;
            case "west" -> Direction.WEST;
            case "east" -> Direction.EAST;
            default -> throw new IllegalStateException("Unexpected value: " + this.endRoad);
        };
    }

}
