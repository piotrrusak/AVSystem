package org.example.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.loader.CommandListWrapper;

import java.io.File;
import java.util.List;

public class DataLoader {

    public static CommandListWrapper LoadJsonData(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filename), CommandListWrapper.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while loading data from JSON.");
            System.exit(1);
        }
        return new CommandListWrapper(List.of());
    }
}
