package org.example.model.intersection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.IntersectionState;
import org.example.model.road.Direction;
import org.example.model.road.OneLaneTwoWayRoad;
import org.example.model.road.Road;
import org.example.model.vehicle.Vehicle;

import java.util.List;

@Getter
@Setter
public class Intersection {

    private IntersectionState intersectionState;

    private Road north;
    private Road south;
    private Road west;
    private Road east;

    public Intersection(IntersectionState intersectionState) {
        this.intersectionState = intersectionState;
        this.north = new OneLaneTwoWayRoad(Direction.NORTH);
        this.south = new OneLaneTwoWayRoad(Direction.SOUTH);
        this.west = new OneLaneTwoWayRoad(Direction.WEST);
        this.east = new OneLaneTwoWayRoad(Direction.EAST);
    }

    public Road getRoadByDirection(Direction direction) {
        return switch(direction) {
            case NORTH -> this.north;
            case SOUTH -> this.south;
            case WEST -> this.west;
            case EAST -> this.east;
        };
    }

    public List<Road> getRoads() {
        return List.of(this.north, this.south, this.west, this.east);
    }

    public void addVehicle(Vehicle vehicle) {
        intersectionState.addVehicle(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        intersectionState.removeVehicle(vehicle);
    }

    public void updateLights() {
        for(Road road : this.getRoads()) {
            this.intersectionState.updateIntersectionLightState(road);
        }
    }

}