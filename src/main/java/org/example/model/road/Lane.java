package org.example.model.road;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.light.TrafficLight;
import org.example.model.vehicle.Vehicle;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
public class Lane {

    private Queue<Vehicle> vehicles;

    public Lane() {
        this.vehicles = new LinkedList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public Vehicle getNextVehicle() {
        return this.vehicles.peek();
    }

}
