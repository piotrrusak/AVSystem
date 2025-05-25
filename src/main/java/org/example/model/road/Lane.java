package org.example.model.road;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.vehicle.Vehicle;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
public class Lane {

    private Queue<Vehicle> vehicles;

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public Vehicle getNextVehicle() {
        return this.vehicles.peek();
    }

    public Lane() {
        this.vehicles = new LinkedList<>();
    }

}
