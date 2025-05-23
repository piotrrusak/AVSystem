package org.example.model.road;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OneLaneTwoWayRoad extends Road {

    public OneLaneTwoWayRoad(Direction direction) {
        super(direction);
    }

}
