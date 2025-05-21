package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.light.Signal;
import org.example.model.road.Direction;
import org.example.model.vehicle.Vehicle;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntersectionState {

    private Map<Direction, List<Vehicle>> waitingVehicles;
    private Map<Direction, Signal> intersectionLightsState;

    public void addVehicle(Vehicle vehicle) {
        this.waitingVehicles.get(vehicle.getStartRoad()).add(vehicle);
    }

}
