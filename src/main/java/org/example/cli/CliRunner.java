package org.example.cli;

import org.example.cli.controller.ConsoleController;
import org.example.cli.controller.command.CommandRegistry;
import org.example.cli.controller.command.OrderDrinkCommand;
import org.example.cli.controller.command.OrderLunchCommand;
import org.example.cli.controller.command.ShowSummaryAndExitCommand;
import org.example.cli.view.ConsoleView;
import org.example.cli.view.InputReader;
import org.example.service.DessertService;
import org.example.service.DrinkService;
import org.example.service.MainCourseService;
import org.example.service.OrderService;

public class CliRunner {

    private final ConsoleController consoleController;

    public CliRunner(
            MainCourseService mainCourseService,
            DessertService dessertService,
            DrinkService drinkService,
            OrderService orderService
    ) {
        ConsoleView consoleView = new ConsoleView();
        InputReader inputReader = new InputReader(consoleView);
        CommandRegistry commandRegistry = registerCommands(
                mainCourseService,
                dessertService,
                drinkService,
                orderService,
                consoleView,
                inputReader
        );
        this.consoleController = new ConsoleController(commandRegistry, inputReader, consoleView);
    }

    public void run() {
        consoleController.start();
    }

    private CommandRegistry registerCommands(
            MainCourseService mainCourseService,
            DessertService dessertService,
            DrinkService drinkService,
            OrderService orderService,
            ConsoleView consoleView,
            InputReader inputReader
    ) {
        CommandRegistry commandRegistry = new CommandRegistry();
        OrderLunchCommand orderLunchCommand =
                new OrderLunchCommand(mainCourseService, dessertService, orderService, inputReader, consoleView);
        commandRegistry.register("0", orderLunchCommand);
        OrderDrinkCommand orderDrinkCommand = new OrderDrinkCommand(drinkService, orderService, inputReader, consoleView);
        commandRegistry.register("1", orderDrinkCommand);
        ShowSummaryAndExitCommand showSummaryAndExitCommand = new ShowSummaryAndExitCommand(orderService, consoleView);
        commandRegistry.register("2", showSummaryAndExitCommand);

        return commandRegistry;
    }

}
