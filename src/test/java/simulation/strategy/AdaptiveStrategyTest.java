package simulation.strategy;

import org.example.model.IntersectionState;
import org.example.model.Route;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.road.Direction;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleStatus;
import org.example.simulation.strategy.AdaptiveStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdaptiveStrategyTest {

    private AdaptiveStrategy adaptiveStrategy;
    private Intersection intersection;

    @BeforeEach
    public void setup() {
        this.intersection = new Intersection(new IntersectionState());
        this.adaptiveStrategy = new AdaptiveStrategy(1, 10, 1, 1);
    }

    @Test
    public void setupTest() {
        this.adaptiveStrategy.setup(intersection);

        Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.NORTH));
        Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.SOUTH));
        Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.EAST));
        Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.WEST));
    }

    @Test
    public void minGreenTimeTest() {
        adaptiveStrategy.setup(intersection);

        adaptiveStrategy.step(intersection);

        Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.NORTH));
        Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.SOUTH));
        Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.WEST));
        Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.EAST));

        adaptiveStrategy.step(intersection);

        Assertions.assertNotEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.NORTH));
        Assertions.assertNotEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.SOUTH));
        Assertions.assertNotEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.WEST));
        Assertions.assertNotEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.EAST));
    }

    @Test
    public void maxGreenTimeTest() {
        Vehicle vehicle = new Vehicle("vehicle", VehicleStatus.WAITING, new Route(Direction.NORTH, Direction.SOUTH));
        for(int i = 0; i < 10; i++) {
            intersection.addVehicle(vehicle);
        }

        adaptiveStrategy.setup(intersection);

        for(int i = 0; i < 10; i++) {
            adaptiveStrategy.step(intersection);
        }

        Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.NORTH));
        Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.SOUTH));
        Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.WEST));
        Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.EAST));

        adaptiveStrategy.step(intersection);

        Assertions.assertNotEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.NORTH));
        Assertions.assertNotEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.SOUTH));
        Assertions.assertNotEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.WEST));
        Assertions.assertNotEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(Direction.EAST));
    }

    @Test
    public void caluculateGreenTimeTest() {
        Vehicle vehicle = new Vehicle("vehicle", VehicleStatus.WAITING, new Route(Direction.NORTH, Direction.SOUTH));
        for(int i = 0; i < 6; i++) {
            intersection.addVehicle(vehicle);
        }

        adaptiveStrategy.setup(intersection);

        Assertions.assertEquals(7, adaptiveStrategy.getCurrentGreenDuration());

    }

}
