package org.example.cli.controller.command;

import org.example.cli.view.ConsoleView;
import org.example.service.OrderService;

public class ShowSummaryAndExitCommand implements Command {

    private final OrderService orderService;
    private final ConsoleView consoleView;

    public ShowSummaryAndExitCommand (
            OrderService orderService,
            ConsoleView consoleView
    ) {
        this.orderService = orderService;
        this.consoleView = consoleView;
    }

    @Override
    public boolean execute() {
        String summary = orderService.getOrderSummary();
        consoleView.printLine(summary);

        return false;
    }

}