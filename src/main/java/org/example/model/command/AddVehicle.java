package org.example.model.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddVehicle extends Command {

    private String vehicleId;
    private String startRoad;
    private String endRoad;

}
