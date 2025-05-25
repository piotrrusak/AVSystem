package simulation.strategy;

import org.example.model.IntersectionState;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.road.Direction;
import org.example.simulation.strategy.EveryTickToggleStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EveryTickToggleStrategyTest {

    private EveryTickToggleStrategy everyTickToggleStrategy;
    private Intersection intersection;

    @BeforeEach
    public void setup() {
        this.everyTickToggleStrategy = new EveryTickToggleStrategy();
        this.intersection = new Intersection(new IntersectionState());
    }

    @Test
    public void setupTest() {
        everyTickToggleStrategy.setup(intersection);

        for(Direction direction : Direction.values()) {
            for(Direction temp : Direction.values()) {
                if(direction == temp) {
                    continue;
                }
                if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                    Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                } else {
                    Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                }
            }
        }
    }

    @Test
    public void toggleCycleTest() {
        everyTickToggleStrategy.setup(intersection);
        everyTickToggleStrategy.step(intersection);

        for(Direction direction : Direction.values()) {
            for(Direction temp : Direction.values()) {
                if(direction == temp) {
                    continue;
                }
                if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                    Assertions.assertEquals(Signal.YELLOW, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                } else {
                    Assertions.assertEquals(Signal.RED_YELLOW, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                }
            }
        }

        everyTickToggleStrategy.step(intersection);

        for(Direction direction : Direction.values()) {
            for(Direction temp : Direction.values()) {
                if(direction == temp) {
                    continue;
                }
                if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                    Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                } else {
                    Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                }
            }
        }

        everyTickToggleStrategy.step(intersection);

        for(Direction direction : Direction.values()) {
            for(Direction temp : Direction.values()) {
                if(direction == temp) {
                    continue;
                }
                if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                    Assertions.assertEquals(Signal.RED_YELLOW, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                } else {
                    Assertions.assertEquals(Signal.YELLOW, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                }
            }
        }

        everyTickToggleStrategy.step(intersection);

        for(Direction direction : Direction.values()) {
            for(Direction temp : Direction.values()) {
                if(direction == temp) {
                    continue;
                }
                if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                    Assertions.assertEquals(Signal.GREEN, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                } else {
                    Assertions.assertEquals(Signal.RED, intersection.getIntersectionState().getIntersectionLightsState().get(direction).get(temp));
                }
            }
        }
    }
}
