package org.example.model.intersection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.IntersectionState;
import org.example.model.light.Signal;
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
            case Direction.NORTH -> this.north;
            case Direction.SOUTH -> this.south;
            case Direction.WEST -> this.west;
            case Direction.EAST -> this.east;
        };
    }

    public List<Road> getRoads() {
        return List.of(this.north, this.south, this.west, this.east);
    }

    public void addVehicle(Vehicle vehicle) {
        if(vehicle.getRoute().isTurningLeft() || vehicle.getRoute().isGoingForward()) {
            this.getRoads().stream().filter(road -> road.getDirection().equals(vehicle.getRoute().getStart())).forEach(road -> road.getEntryLanes().getFirst().addVehicle(vehicle));
        } else if(vehicle.getRoute().isTurningRight() || vehicle.getRoute().isGoingForward()) {
            this.getRoads().stream().filter(road -> road.getDirection().equals(vehicle.getRoute().getStart())).forEach(road -> road.getEntryLanes().getLast().addVehicle(vehicle));
        }
        intersectionState.addVehicle(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.getRoadByDirection(vehicle.getRoute().getStart())
                .getEntryLanes().stream()
                .filter(lane -> lane.getVehicles().contains(vehicle))
                .findFirst()
                .ifPresent(lane -> lane.getVehicles().remove(vehicle));
        intersectionState.removeVehicle(vehicle);
    }

    public void updateLights() {
        for(Road road : this.getRoads()) {
            this.intersectionState.updateIntersectionLightState(road);
        }
    }
}