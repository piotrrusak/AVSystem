package org.example.simulation;

import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.road.Lane;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;

import java.util.*;

public class VehicleManager {

    public List<Vehicle> step(Intersection intersection) {

        List<Vehicle> leavingVehicles = new ArrayList<>();

        var readyToGo = intersection.getRoads().stream()
                .filter(road -> road.getTrafficLight().getState() == Signal.GREEN)
                .flatMap(road -> road.getEntryLanes().stream().map(Lane::getNextVehicle).filter(Objects::nonNull))
                .toList();

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
