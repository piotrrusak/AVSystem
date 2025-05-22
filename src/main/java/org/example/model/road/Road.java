package org.example.model.road;

import lombok.Getter;
import lombok.Setter;
import org.example.model.light.TrafficLight;

import java.util.List;

@Getter
@Setter
public abstract class Road {

    protected TrafficLight trafficLight;

    protected Direction direction;

    protected List<Lane> entryLanes;

    protected List<Lane> exitLanes;

}
