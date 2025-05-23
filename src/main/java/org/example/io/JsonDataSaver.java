package org.example.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.loader.StepStatuses;
import org.example.model.vehicle.Vehicle;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JsonDataSaver {
    public static void SaveJsonData(String filePath, StepStatuses stepStatuses) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), stepStatuses);
    }
}
