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

    private Map<Direction, TrafficLight> trafficLights;

    protected List<Lane> entryLanes;

    public Road(Direction direction) {
        this.direction = direction;
        this.entryLanes = List.of(new Lane());
        this.trafficLights = new HashMap<>();
        for(Direction temp : Direction.values()) {
            if(temp == direction) {
                continue;
            }
            this.trafficLights.put(temp, new TrafficLight());
        }
    }

}