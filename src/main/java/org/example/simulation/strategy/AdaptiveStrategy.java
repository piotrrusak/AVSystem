package org.example.simulation.strategy;

import lombok.RequiredArgsConstructor;
import org.example.model.intersection.Intersection;
import org.example.model.light.Signal;
import org.example.model.light.TrafficLight;
import org.example.model.road.Direction;
import org.example.model.road.Road;

import java.util.List;

@RequiredArgsConstructor
public class AdaptiveStrategy implements TrafficLightStrategy {

    private int minGreenTime;
    private int maxGreenTime;
    private int transitionTime;
    private double vehicleWeight;

    private boolean inTransitionState = false;
    private int currentStep = 0;
    private int currentGreenDuration = minGreenTime;

    public AdaptiveStrategy(int minGreenTime, int maxGreenTime, int transitionTime, double vehicleWeight) {
        this.minGreenTime = minGreenTime;
        this.maxGreenTime = maxGreenTime;
        this.transitionTime = transitionTime;
        this.vehicleWeight = vehicleWeight;
    }

    @Override
    public void setup(Intersection intersection) {
        for (Road road : intersection.getRoads()) {
            if (road.getDirection() == Direction.NORTH ||
                    road.getDirection() == Direction.SOUTH) {
                road.getTrafficLight().setState(Signal.GREEN);
            } else {
                road.getTrafficLight().setState(Signal.RED);
            }
        }
        intersection.updateLights();
        currentGreenDuration = calculateGreenTime(intersection);
    }

    @Override
    public void step(Intersection intersection) {
        List<TrafficLight> trafficLights = intersection.getRoads().stream().map(Road::getTrafficLight).toList();

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
        Signal currentState = null;

        for (Road road : intersection.getRoads()) {
            Signal state = road.getTrafficLight().getState();
            if (currentState == null) {
                currentState = state;
            }

            if ((state == Signal.RED &&
                    currentState == Signal.RED) ||
                    (state == Signal.GREEN &&
                            currentState == Signal.GREEN)) {
                totalWaitingVehicles += intersection.getIntersectionState().getWaitingVehicles().get(road.getDirection()).size();
            }
        }


        int additionalTime = (int) (totalWaitingVehicles * vehicleWeight);
        int calculatedDuration = minGreenTime + additionalTime;

        return Math.min(calculatedDuration, maxGreenTime);
    }
}
