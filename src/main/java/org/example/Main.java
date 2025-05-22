package org.example;

import org.example.io.JsonDataLoader;
import org.example.model.IntersectionState;
import org.example.model.command.AddVehicle;
import org.example.model.command.Command;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.loader.CommandListWrapper;
import org.example.model.road.Direction;
import org.example.model.road.Road;
import org.example.model.road.TwoWayRoad;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;
import org.example.simulation.IntersectionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CommandListWrapper commandListWrapper = JsonDataLoader.LoadJsonData("/home/piotr/IdeaProjects/AVSystem/src/main/resources/json_input_data.json");

        System.out.println(commandListWrapper.getCommands());


        IntersectionState intersectionState = new IntersectionState();

        Intersection intersection = new Intersection(intersectionState, new TwoWayRoad(), new TwoWayRoad(), new TwoWayRoad(), new TwoWayRoad());

        for(Command command : commandListWrapper.getCommands()) {
            if(command instanceof AddVehicle) {
                System.out.println("AddVehicle");
                intersection.getIntersectionState().addVehicle(new Vehicle(
                                                                           VehicleStatus.WAITING,
                                                                           ((AddVehicle) command).getStartRoadAsDirection(),
                                                                           ((AddVehicle) command).getEndRoadAsDirection()
                ));
            } else {
                System.out.println("Step");
            }
        }

        System.out.println(intersection.getIntersectionState().getWaitingVehicles());

    }
}