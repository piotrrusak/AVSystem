package org.example.model.road;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.light.TrafficLight;
import org.example.model.vehicle.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class Road {

    protected Direction direction;
    protected TrafficLight trafficLight;

    protected List<Lane> entryLanes;

    protected List<Lane> exitLanes;

    public Road(Direction direction) {
        this.direction = direction;
        this.trafficLight = new TrafficLight();
        this.entryLanes = List.of(new Lane());
        this.exitLanes = List.of(new Lane());
    }

}