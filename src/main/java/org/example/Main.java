package org.example;

import org.example.io.DataLoader;
import org.example.io.DataSaver;
import org.example.model.IntersectionState;
import org.example.model.Route;
import org.example.model.command.AddVehicle;
import org.example.model.command.Command;
import org.example.model.intersection.Intersection;
import org.example.model.loader.CommandListWrapper;
import org.example.model.loader.StepStatus;
import org.example.model.loader.StepStatuses;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;
import org.example.simulation.VehicleManager;
import org.example.simulation.strategy.AdaptiveStrategy;
import org.example.simulation.strategy.TrafficLightStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        CommandListWrapper commandListWrapper = DataLoader.LoadJsonData("/home/piotr/IdeaProjects/AVSystem/src/main/resources/input_data.json");
        IntersectionState intersectionState = new IntersectionState();
        Intersection intersection = new Intersection(intersectionState);
        TrafficLightStrategy strategy = new AdaptiveStrategy(1, 10, 1, 1);

        strategy.setup(intersection);

        VehicleManager vehicleManager = new VehicleManager();
        StepStatuses stepStatuses = new StepStatuses();

        for(Command command : commandListWrapper.getCommands()) {

            if(command instanceof AddVehicle) {
                // Dodawanie nowego pojazdu zczytanego z jsona
                intersection.getIntersectionState().addVehicle(new Vehicle(
                                                                           ((AddVehicle) command).getVehicleId(),
                                                                           VehicleStatus.WAITING,
                                                                           new Route(((AddVehicle) command).getStartRoadAsDirection(), ((AddVehicle) command).getEndRoadAsDirection())
                ));
            } else {

                // Zmiana statusu pojazdów, które w tym kroku opuszczą skrzyżowanie na IN_INTERSECTION
                List<Vehicle> leftVehicles = vehicleManager.step(intersection);

                // Dodanie ich do obiektu zapisywane do jsona
                stepStatuses.getStepStatuses().add(new StepStatus(leftVehicles.stream().map(Vehicle::getVehicleId).collect(Collectors.toList())));

                // Usuwanie pojazdów z statusem IN_INTERSECTION
                leftVehicles.forEach(intersection::removeVehicle);

                // Przejście do następnego etapu świateł (w zależności od strategii)
                strategy.step(intersection);

            }

        }

        DataSaver.SaveJsonData("/home/piotr/IdeaProjects/AVSystem/src/main/resources/output_data.json", stepStatuses);

    }
}