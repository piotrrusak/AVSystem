package org.example.model.light;

public interface TrafficLight {

    Signal getState();

    Signal toggleNextState();

}
