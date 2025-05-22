package org.example.simulation;

import org.example.model.IntersectionState;
import org.example.model.light.Signal;
import org.example.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleManager {

    public void step(IntersectionState intersectionState) {

        var readyToGo = intersectionState.getIntersectionLightsState()
                .keySet().stream().filter(key -> intersectionState.getIntersectionLightsState().getOrDefault(key, Signal.RED) == Signal.GREEN)
                .filter(key -> intersectionState.getWaitingVehicles().containsKey(key))
                .flatMap(key -> intersectionState.getWaitingVehicles().get(key).stream()).toList();

    }

}
