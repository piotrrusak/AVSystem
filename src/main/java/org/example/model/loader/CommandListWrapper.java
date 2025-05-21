package org.example.model.loader;

import lombok.Getter;
import lombok.Setter;
import org.example.model.command.Command;

import java.util.List;

@Getter
@Setter
public class CommandListWrapper {

    private List<Command> commands;

    public CommandListWrapper(List<Command> commands) {
        this.commands = commands;
    }

}
