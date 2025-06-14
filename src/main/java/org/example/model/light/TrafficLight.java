package org.example.model.light;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrafficLight {

    private Signal state;

    public TrafficLight() {
        this.state = Signal.RED;
    }

    public void toggleNextState() {
        switch(this.state) {
            case Signal.RED -> state = Signal.RED_YELLOW;
            case Signal.RED_YELLOW -> state = Signal.GREEN;
            case Signal.GREEN -> state = Signal.YELLOW;
            case Signal.YELLOW -> state = Signal.RED;
        }
    }

}
