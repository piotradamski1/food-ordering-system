package org.example.cli.controller;

import org.example.cli.controller.command.Command;
import org.example.cli.controller.command.CommandRegistry;
import org.example.cli.view.ConsoleView;
import org.example.cli.view.InputReader;

public class ConsoleController {

    private final CommandRegistry commandRegistry;
    private final InputReader reader;
    private final ConsoleView view;

    private boolean running = true;

    public ConsoleController(CommandRegistry commandRegistry, InputReader reader, ConsoleView view) {
        this.commandRegistry = commandRegistry;
        this.reader = reader;
        this.view = view;
    }

    public void start() {
        view.printWelcome();
        while (running) {
            handleOrder();
        }
    }

    private void handleOrder() {
        view.printActions();
        String choice = reader.readLine("Select action: ");
        executeCommand(choice);
    }

    private void executeCommand(String choice) {
        Command command = commandRegistry.get(choice);
        if (command != null) {
            this.running = command.execute();
        } else {
            view.printLine("Unknown command: " + choice);
        }
    }

}
