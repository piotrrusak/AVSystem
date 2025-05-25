package org.example.simulation.strategy;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.light.TrafficLight;
import org.example.model.road.Direction;
import org.example.model.road.Road;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AdaptiveStrategy implements TrafficLightStrategy {

    private int minGreenTime;
    private int maxGreenTime;
    private int transitionTime;
    private double vehicleWeight;

    private boolean inTransitionState = false;
    private int currentStep = 0;
    @Getter
    private int currentGreenDuration = minGreenTime;

    public AdaptiveStrategy(int minGreenTime, int maxGreenTime, int transitionTime, double vehicleWeight) {
        this.minGreenTime = minGreenTime;
        this.maxGreenTime = maxGreenTime;
        this.transitionTime = transitionTime;
        this.vehicleWeight = vehicleWeight;
    }

    @Override
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
            }

        }

        intersection.updateLights();
        currentGreenDuration = calculateGreenTime(intersection);
    }

    @Override
    public void step(Intersection intersection) {
        List<TrafficLight> trafficLights = intersection.getRoads().stream()
                .flatMap(road -> Arrays.stream(Direction.values()).map(direction -> road.getTrafficLights().get(direction)))
                .filter(Objects::nonNull)
                .toList();

        if (currentStep >= currentGreenDuration && !inTransitionState) {
            trafficLights.forEach(TrafficLight::toggleNextState);
            inTransitionState = true;
        } else if (currentStep >= transitionTime && inTransitionState) {
            trafficLights.forEach(TrafficLight::toggleNextState);
            inTransitionState = false;
            currentStep = 0;
            currentGreenDuration = calculateGreenTime(intersection);
        } else {
            currentStep++;
        }

        intersection.updateLights();
    }

    private int calculateGreenTime(Intersection intersection) {
        int totalWaitingVehicles = 0;

        for (Road road : intersection.getRoads()) {
            for(Direction direction : Direction.values()) {
                if(road.getDirection() == direction) {
                    continue;
                }
                if ((road.getTrafficLights().get(direction).getState() == Signal.RED)) {
                    totalWaitingVehicles += intersection.getIntersectionState().getWaitingVehicles().get(road.getDirection()).size();
                }
            }
        }

        int additionalTime = (int) (totalWaitingVehicles * vehicleWeight);
        int calculatedDuration = minGreenTime + additionalTime;

        return Math.min(calculatedDuration, maxGreenTime);
    }
}
