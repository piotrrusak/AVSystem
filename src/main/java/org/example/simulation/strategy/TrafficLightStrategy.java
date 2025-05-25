package org.example.simulation.strategy;

import org.example.model.intersection.Intersection;

public interface TrafficLightStrategy {

    void setup(Intersection intersection);

    void step(Intersection intersection);
}
