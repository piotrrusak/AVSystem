package org.example.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.loader.StepStatuses;

import java.io.File;

public class DataSaver {
    public static void SaveJsonData(String filePath, StepStatuses stepStatuses) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), stepStatuses);
    }
}
