package io;

import org.example.io.DataLoader;
import org.example.model.loader.CommandListWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {

    @Test
    public void dataLoaderTest() {
        CommandListWrapper commandListWrapper = DataLoader.LoadJsonData("/src/test/resources/input_data.json");

        Assertions.assertEquals(8, commandListWrapper.getCommands().size());
    }
}
