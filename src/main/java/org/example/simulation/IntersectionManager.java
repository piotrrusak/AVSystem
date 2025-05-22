package org.example.simulation;

import org.example.model.intersection.Intersection;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;

public class IntersectionManager {

    private Intersection intersection;

    public void addVehicle(Vehicle vehicle) {
        intersection.addVehicle(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        intersection.removeVehicle(vehicle);
    }

    public void step() {
        this.intersection.getRoads().forEach(this.intersection.getIntersectionState()::setIntersectionLightState);

        intersection.getRoads().stream().flatMap(road -> road.getEntryLanes().stream()).forEach(lane -> {
            Vehicle vehicle = lane.getNextVehicle();
            if (vehicle != null) {
                vehicle.setStatus(VehicleStatus.APPROACHING);
                this.intersection.getIntersectionState().addVehicle(vehicle);
            }
        });

    }

}
