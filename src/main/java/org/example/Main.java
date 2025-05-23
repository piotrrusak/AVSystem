package org.example;

import org.example.io.JsonDataLoader;
import org.example.io.JsonDataSaver;
import org.example.model.IntersectionState;
import org.example.model.Route;
import org.example.model.command.AddVehicle;
import org.example.model.command.Command;
import org.example.model.intersection.Intersection;
import org.example.model.loader.CommandListWrapper;
import org.example.model.loader.StepStatus;
import org.example.model.loader.StepStatuses;
import org.example.model.road.Direction;
import org.example.model.road.OneLaneTwoWayRoad;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;
import org.example.simulation.IntersectionManager;
import org.example.simulation.VehicleManager;
import org.example.simulation.strategy.AdaptiveStrategy;
import org.example.simulation.strategy.EveryTickToggleStrategy;
import org.example.simulation.strategy.TrafficLightStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        CommandListWrapper commandListWrapper = JsonDataLoader.LoadJsonData("/home/piotr/IdeaProjects/AVSystem/src/main/resources/json_input_data.json");

        System.out.println(commandListWrapper.getCommands());

        IntersectionState intersectionState = new IntersectionState();

        Intersection intersection = new Intersection(intersectionState, new OneLaneTwoWayRoad(Direction.NORTH), new OneLaneTwoWayRoad(Direction.EAST), new OneLaneTwoWayRoad(Direction.SOUTH), new OneLaneTwoWayRoad(Direction.WEST));

        TrafficLightStrategy strategy = new AdaptiveStrategy();
        strategy.setup(intersection);

        VehicleManager vehicleManager = new VehicleManager();

        StepStatuses stepStatuses = new StepStatuses();

        for(Command command : commandListWrapper.getCommands()) {

            if(command instanceof AddVehicle) {
                System.out.println("AddVehicle");
                intersection.getIntersectionState().addVehicle(new Vehicle(
                                                                           ((AddVehicle) command).getVehicleId(),
                                                                           VehicleStatus.WAITING,
                                                                           new Route(((AddVehicle) command).getStartRoadAsDirection(), ((AddVehicle) command).getEndRoadAsDirection())
                ));
            } else {
                System.out.println("Step");

                List<Vehicle> leftVehicles = vehicleManager.step(intersection);

                stepStatuses.getStepStatuses().add(new StepStatus(leftVehicles.stream().map(Vehicle::getVehicleId).collect(Collectors.toList())));

                leftVehicles.forEach(intersection::removeVehicle);

                strategy.step(intersection);

            }

        }

        JsonDataSaver.SaveJsonData("/home/piotr/IdeaProjects/AVSystem/src/main/resources/json_output_data.json", stepStatuses);

    }
}