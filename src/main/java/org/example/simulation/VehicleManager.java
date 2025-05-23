package org.example.simulation;

import org.example.model.IntersectionState;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.road.Direction;
import org.example.model.road.Road;
import org.example.model.vehicle.Vehicle;

import java.util.*;

public class VehicleManager {

    public List<Vehicle> step(Intersection intersection) {
        var ready = Arrays.stream(Direction.values()).filter(direction -> intersection.getIntersectionState().getIntersectionLightsState().get(direction).equals(Signal.GREEN)).flatMap(key -> intersection.getIntersectionState().getWaitingVehicles().get(key).stream()).toList();
        return ready;
    }

}
