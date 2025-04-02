package org.example.cli.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();

    public void register(String key, Command command) {
        commands.put(key, command);
    }

    public Command get(String key) {
        return commands.get(key);
    }

}
