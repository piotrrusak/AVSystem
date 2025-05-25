package model.intersection;

import org.example.model.IntersectionState;
import org.example.model.Route;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.light.TrafficLight;
import org.example.model.road.Direction;
import org.example.model.road.Road;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        for(Road road : intersection.getRoads()) {
            Map<Direction, TrafficLight> lightMap = new HashMap<>();
            for(Direction direction : Direction.values()) {
                TrafficLight trafficLight = new TrafficLight();
                trafficLight.setState(Signal.GREEN);
                lightMap.put(direction, trafficLight);
            }
            road.setTrafficLights(lightMap);
        }

        intersection.updateLights();

        Arrays.stream(Direction.values()).forEach(key -> Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(key).get(Direction.SOUTH)));
        Arrays.stream(Direction.values()).forEach(key -> Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(key).get(Direction.WEST)));
        Arrays.stream(Direction.values()).forEach(key -> Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(key).get(Direction.EAST)));
    }
}
