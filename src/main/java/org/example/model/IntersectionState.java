package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.light.Signal;
import org.example.model.road.Direction;
import org.example.model.road.Road;
import org.example.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class IntersectionState {

    private Map<Direction, List<Vehicle>> waitingVehicles;
    private Map<Direction, Signal> intersectionLightsState;

    public IntersectionState() {
        Map<Direction, List<Vehicle>> waitingVehicles = new HashMap<>();
        for(Direction direction : Direction.values()) {
            waitingVehicles.put(direction, new ArrayList<>());
        }

        Map<Direction, Signal> intersectionLightsState = new HashMap<>();
        for(Direction direction : Direction.values()) {
            intersectionLightsState.put(direction, Signal.RED);
        }

        this.waitingVehicles = waitingVehicles;
        this.intersectionLightsState = intersectionLightsState;
    }

    public void addVehicle(Vehicle vehicle) {
        this.waitingVehicles.get(vehicle.getStartDirection()).add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.waitingVehicles.get(vehicle.getEndDirection()).remove(vehicle);
    }

    public void setIntersectionLightState(Road road) {
        this.intersectionLightsState.put(road.getDirection(), road.getTrafficLight().getState());
    }

}
