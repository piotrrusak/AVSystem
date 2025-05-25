package org.example.simulation.strategy;

import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.light.TrafficLight;
import org.example.model.road.Direction;
import org.example.model.road.Road;

public class EveryTickToggleStrategy implements TrafficLightStrategy {

    public void setup(Intersection intersection) {
        for(Road road : intersection.getRoads()) {
            for(Direction direction : Direction.values()) {
                if(road.getDirection() == direction) {
                    continue;
                }
                if(road.getDirection() == Direction.NORTH || road.getDirection() == Direction.SOUTH) {
                    road.getTrafficLights().get(direction).setState(Signal.GREEN);
                } else {
                    road.getTrafficLights().get(direction).setState(Signal.RED);
                }

                intersection.updateLights();
            }

        }
    }

    public void step(Intersection intersection) {

        for(Road road : intersection.getRoads()) {
            for(Direction direction : Direction.values()) {
                if(road.getDirection() == direction) {
                    continue;
                }
                road.getTrafficLights().get(direction).toggleNextState();
            }
        }

        intersection.updateLights();
    }
}
