package org.example.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.road.Direction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddVehicle extends Command {

    private String vehicleId;
    private String startRoad;
    private String endRoad;

    public Direction getStartRoadAsDirection() {
        return switch(this.startRoad) {
            case "north" -> Direction.NORTH;
            case "south" -> Direction.SOUTH;
            case "west" -> Direction.WEST;
            case "east" -> Direction.EAST;
            default -> throw new IllegalStateException("Unexpected value: " + this.startRoad);
        };
    }

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
