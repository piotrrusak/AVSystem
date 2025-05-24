package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.model.command.AddVehicle;
import org.example.model.command.Command;
import org.example.model.command.Step;
import org.example.model.loader.CommandListWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private static final String[] ROADS = { "north", "south", "east", "west" };
    private static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Command> commands = generateRandomCommands(20);
        CommandListWrapper result = new CommandListWrapper(commands);

        File outputFile = new File("/home/piotr/IdeaProjects/AVSystem/src/main/resources/input_data.json");
        mapper.writeValue(outputFile, result);

        System.out.println("Zapisano dane do pliku: " + outputFile.getAbsolutePath());
    }


    private static List<Command> generateRandomCommands(int count) {
        List<Command> commands = new ArrayList<>();
        int vehicleCounter = 1;

        for (int i = 0; i < count; i++) {
            if (random.nextDouble() < 0.6) {
                String vehicleId = "vehicle" + vehicleCounter++;
                String startRoad = randomRoad();
                String endRoad;
                do {
                    endRoad = randomRoad();
                } while (endRoad.equals(startRoad));
                commands.add(new AddVehicle(vehicleId, startRoad, endRoad));
            } else {
                commands.add(new Step());
            }
        }

        return commands;
    }

    private static String randomRoad() {
        return ROADS[random.nextInt(ROADS.length)];
    }
}
