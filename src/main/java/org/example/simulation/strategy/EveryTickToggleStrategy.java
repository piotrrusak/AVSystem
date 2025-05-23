package org.example.simulation.strategy;

import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.light.TrafficLight;
import org.example.model.road.Direction;
import org.example.model.road.Road;

public class EveryTickToggleStrategy implements TrafficLightStrategy {

    public void setup(Intersection intersection) {
        for(Road road : intersection.getRoads()) {
            if(road.getDirection() == Direction.NORTH || road.getDirection() == Direction.SOUTH) {
                road.getTrafficLight().setState(Signal.GREEN);
            } else {
                road.getTrafficLight().setState(Signal.RED);
            }

            intersection.updateLights();
        }
    }

    public void step(Intersection intersection) {

        for(Road road : intersection.getRoads()) {
            road.getTrafficLight().toggleNextState();
        }

        intersection.updateLights();
    }
}
