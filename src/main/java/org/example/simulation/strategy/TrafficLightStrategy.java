package org.example.simulation.strategy;

import org.example.model.intersection.Intersection;

public interface TrafficLightStrategy {

    public void setup(Intersection intersection);

    public void step(Intersection intersection);
}
