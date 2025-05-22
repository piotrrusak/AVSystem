package org.example.model.loader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StepStatuses {

    private List<StepStatus> stepStatuses;

    public StepStatuses() {
        this.stepStatuses = new ArrayList<>();
    }
}
