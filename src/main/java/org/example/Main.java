package org.example;

import org.example.io.JsonDataLoader;
import org.example.model.loader.CommandListWrapper;

public class Main {
    public static void main(String[] args) {

        CommandListWrapper commandListWrapper = JsonDataLoader.LoadJsonData("/home/piotr/IdeaProjects/AVSystem/src/main/resources/json_input_data.json");

        System.out.println(commandListWrapper.getCommands());

    }
}