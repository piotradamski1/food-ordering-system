package org.example.cli.controller.command;

import org.example.cli.view.ConsoleView;
import org.example.cli.view.InputReader;
import org.example.model.*;
import org.example.service.DrinkService;
import org.example.service.OrderService;

import java.util.List;

public class OrderDrinkCommand implements Command {

    private final DrinkService drinkService;
    private final OrderService orderService;
    private final InputReader inputReader;
    private final ConsoleView consoleView;

    public OrderDrinkCommand (
            DrinkService drinkService,
            OrderService orderService,
            InputReader inputReader,
            ConsoleView consoleView
    ) {
        this.drinkService = drinkService;
        this.orderService = orderService;
        this.inputReader = inputReader;
        this.consoleView = consoleView;
    }

    @Override
    public boolean execute() {
        Drink drink = handleDrinkOrder();
        Drink drinkWithAdditions = createDrinkWithAdditions(drink);
        orderService.addDrink(drinkWithAdditions);

        return true;
    }

    private Drink handleDrinkOrder() {
        List<Drink> drinks = drinkService.getAll();
        consoleView.writeMenuItems(drinks);
        int maxIndex = drinks.size();
        int selectedDrinkIndex = inputReader.readValidIndex("Choose Drink: ", maxIndex);

        return drinks.get(selectedDrinkIndex);
    }

    private Drink createDrinkWithAdditions(Drink drink) {
        boolean shouldAddIce = inputReader.readYesNo("Do you want to add ice?");
        drink.setWithIce(shouldAddIce);
        boolean shouldAddLemon = inputReader.readYesNo("Do you want to add lemon?");
        drink.setWithLemon(shouldAddLemon);

        return drink;
    }

}