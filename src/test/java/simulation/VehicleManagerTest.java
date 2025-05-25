package simulation;

import org.example.model.IntersectionState;
import org.example.model.Route;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.road.Direction;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;
import org.example.simulation.VehicleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleManagerTest {

    private VehicleManager vehicleManager;
    private Intersection intersection;

    @BeforeEach
    public void setup() {
        vehicleManager = new VehicleManager();
        intersection = new Intersection(new IntersectionState());
    }

    @Test
    void testStepWithNoVehicles() {
        List<Vehicle> leftVehicles = vehicleManager.step(intersection);
        assertTrue(leftVehicles.isEmpty(), "No vehicles should leave when no vehicles are present");
    }

    @Test
    void testStepWithVehiclesAtRedLight() {
        Vehicle vehicle = new Vehicle("vehicle1", VehicleStatus.WAITING, new Route(Direction.NORTH, Direction.SOUTH));

        intersection.getIntersectionState().getIntersectionLightsState().put(Direction.NORTH, Signal.RED);
        intersection.addVehicle(vehicle);

        List<Vehicle> leftVehicles = vehicleManager.step(intersection);

        assertTrue(leftVehicles.isEmpty(), "No vehicles should leave at red light");
        assertEquals(VehicleStatus.WAITING, vehicle.getStatus());
    }

    @Test
    void testStepWithVehiclesAtGreenLight() {
        Vehicle vehicle = new Vehicle("vehicle1", VehicleStatus.WAITING, new Route(Direction.NORTH, Direction.SOUTH));

        intersection.getRoadByDirection(Direction.NORTH).getTrafficLight().setState(Signal.GREEN);
        intersection.addVehicle(vehicle);
        vehicleManager.step(intersection);

        assertEquals(VehicleStatus.IN_INTERSECTION, vehicle.getStatus());
    }

    @Test
    void testStepWithConflict() {
        intersection.getRoadByDirection(Direction.NORTH).getTrafficLight().setState(Signal.GREEN);
        intersection.getRoadByDirection(Direction.SOUTH).getTrafficLight().setState(Signal.GREEN);

        Vehicle vehicle1 = new Vehicle("vehicle1", VehicleStatus.WAITING, new Route(Direction.NORTH, Direction.SOUTH));
        Vehicle vehicle2 = new Vehicle("vehicle2", VehicleStatus.WAITING, new Route(Direction.SOUTH, Direction.WEST));

        intersection.addVehicle(vehicle1);
        intersection.addVehicle(vehicle2);
        vehicleManager.step(intersection);

        assertEquals(VehicleStatus.IN_INTERSECTION, vehicle1.getStatus());
        assertEquals(VehicleStatus.WAITING, vehicle2.getStatus());
    }
}
