package org.example.model.loader;

import lombok.Getter;
import lombok.Setter;
import org.example.model.vehicle.Vehicle;

import java.util.List;

@Getter
@Setter
public class StepStatus {
    private List<String> leftVehicles;

    public StepStatus() {}

    public StepStatus(List<String> leftVehicles) {
        this.leftVehicles = leftVehicles;
    }

}