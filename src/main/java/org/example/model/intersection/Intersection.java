package org.example.model.intersection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.road.Direction;
import org.example.model.road.Road;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Intersection {

    private Road north;
    private Road south;
    private Road west;
    private Road east;

    public Road getRoadByDirection(Direction direction) {
        return switch(direction) {
            case NORTH -> this.north;
            case SOUTH -> this.south;
            case WEST -> this.west;
            case EAST -> this.east;
        };
    }

}