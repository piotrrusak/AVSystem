package org.example.simulation;

import org.example.model.IntersectionState;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.road.Direction;
import org.example.model.road.Road;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;

import java.util.*;

public class VehicleManager {

    public List<Vehicle> step(Intersection intersection) {

        List<Vehicle> leavingVehicles = new ArrayList<>();

        var readyToGo = Arrays.stream(Direction.values())
                .filter(direction -> intersection.getIntersectionState().getIntersectionLightsState().get(direction).equals(Signal.GREEN))
                .flatMap(key -> intersection.getIntersectionState().getWaitingVehicles().get(key).stream()).toList();

        for (var vehicle : readyToGo) {
            boolean isClearToGo = true;
            for (var otherVehicle : readyToGo) {
                if (vehicle.equals(otherVehicle)) continue;
                if (!vehicle.getRoute().hasRoutePriority(otherVehicle.getRoute()) && otherVehicle.getStatus() == VehicleStatus.IN_INTERSECTION) {
                    isClearToGo = false;
                    break;
                }
            }
            if (isClearToGo) {
                vehicle.setStatus(VehicleStatus.IN_INTERSECTION);
                leavingVehicles.add(vehicle);
            }
        }

        return leavingVehicles;
    }

}
