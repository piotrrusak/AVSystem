package model.intersection;

import org.example.model.IntersectionState;
import org.example.model.Route;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.road.Direction;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class IntersectionTest {

    private Intersection intersection;

    @BeforeEach
    public void setup() {
        intersection = new Intersection(new IntersectionState());
    }

    @Test
    public void addVehicleTest() {
        Vehicle vehicle = new Vehicle("vehicle1", VehicleStatus.WAITING, new Route(Direction.NORTH, Direction.SOUTH));

        intersection.addVehicle(vehicle);

        Assertions.assertTrue(intersection.getIntersectionState().getWaitingVehicles().get(Direction.NORTH).contains(vehicle));
    }

    @Test
    public void removeVehicleTest() {
        Vehicle vehicle = new Vehicle("vehicle1", VehicleStatus.WAITING, new Route(Direction.NORTH, Direction.SOUTH));

        intersection.getIntersectionState().getWaitingVehicles().get(Direction.NORTH).add(vehicle);
        intersection.removeVehicle(vehicle);

        Assertions.assertFalse(intersection.getIntersectionState().getWaitingVehicles().get(Direction.NORTH).contains(vehicle));
    }

    @Test
    public void updateIntersectionLightStateTest() {
        intersection.getRoads().forEach(key -> key.getTrafficLight().setState(Signal.GREEN));

        Arrays.stream(Direction.values()).forEach(key -> Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(key)));

        intersection.updateLights();

        Arrays.stream(Direction.values()).forEach(key -> Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(key)));
    }
}
