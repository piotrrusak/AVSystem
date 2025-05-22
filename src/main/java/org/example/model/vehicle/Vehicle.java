package org.example.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.road.Direction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private VehicleStatus status;
    private Direction startDirection;
    private Direction endDirection;

}
