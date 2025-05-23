package org.example.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Route;
import org.example.model.road.Direction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private String vehicleId;
    private VehicleStatus status;
    private Route route;

}
