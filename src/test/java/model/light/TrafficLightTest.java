package model.light;

import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.light.TrafficLight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrafficLightTest {

    private TrafficLight trafficLight;

    @BeforeEach
    public void setup() {
        this.trafficLight = new TrafficLight();
    }

    @Test
    public void setTrafficLightSignal() {
        trafficLight.setState(Signal.GREEN);

        Assertions.assertEquals(Signal.GREEN, trafficLight.getState());
    }

    @Test
    public void toggleTrafficLightTest() {
        trafficLight.setState(Signal.GREEN);

        trafficLight.toggleNextState();

        Assertions.assertEquals(Signal.YELLOW, trafficLight.getState());
    }

    @Test
    public void trafficLightSignalCycleTest() {
        trafficLight.setState(Signal.RED);

        trafficLight.toggleNextState();
        Assertions.assertEquals(Signal.RED_YELLOW, trafficLight.getState());
        trafficLight.toggleNextState();
        Assertions.assertEquals(Signal.GREEN, trafficLight.getState());
        trafficLight.toggleNextState();
        Assertions.assertEquals(Signal.YELLOW, trafficLight.getState());
        trafficLight.toggleNextState();
        Assertions.assertEquals(Signal.RED, trafficLight.getState());
    }
}
