package org.example.cli.controller.command;

import org.example.cli.view.ConsoleView;
import org.example.cli.view.InputReader;
import org.example.model.*;
import org.example.service.DessertService;
import org.example.service.MainCourseService;
import org.example.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderLunchCommand implements Command {

    private final MainCourseService mainCourseService;
    private final DessertService dessertService;
    private final OrderService orderService;
    private final InputReader inputReader;
    private final ConsoleView consoleView;

    public OrderLunchCommand(
            MainCourseService mainCourseService,
            DessertService dessertService,
            OrderService orderService,
            InputReader inputReader,
            ConsoleView consoleView
    ) {
        this.mainCourseService = mainCourseService;
        this.dessertService = dessertService;
        this.orderService = orderService;
        this.inputReader = inputReader;
        this.consoleView = consoleView;
    }

    @Override
    public boolean execute() {
        MainCourse mainCourse = handleMainCourseOrder();
        Optional<Dessert> dessert = handleDessertOrder();
        Dessert dessertToAdd = dessert.orElse(null);
        Lunch lunch = new Lunch(mainCourse, dessertToAdd);
        orderService.addLunch(lunch);

        return true;
    }

    private MainCourse handleMainCourseOrder() {
        Cuisine cuisine = getSelectedCuisine();
        List<MainCourse> mainCourses = mainCourseService.getByCuisine(cuisine);
        consoleView.writeMenuItems(mainCourses);
        int maxIndex = mainCourses .size();
        int selectedMainCourseIndex = inputReader.readValidIndex("Choose Main Course: ", maxIndex);

        return mainCourses.get(selectedMainCourseIndex);
    }

    private Cuisine getSelectedCuisine() {
        List<Cuisine> availableCuisines = mainCourseService.getAllAvailableCuisines();
        writeAvailableCuisines(availableCuisines);
        int maxIndex = availableCuisines.size();
        int selectedCuisineIndex = inputReader.readValidIndex("Choose cuisine: ", maxIndex);

        return availableCuisines.get(selectedCuisineIndex);
    }

    private Optional<Dessert> handleDessertOrder() {
        boolean shouldOrderDesert = inputReader.readYesNo("Do you want to order dessert?");
        if (shouldOrderDesert) {
            Dessert dessert = orderDessert();
            return Optional.of(dessert);
        }

        return Optional.empty();
    }

    private Dessert orderDessert() {
        List<Dessert> desserts = dessertService.getAll();
        consoleView.writeMenuItems(desserts);
        int selectedDessertIndex = inputReader.readInt("Choose Dessert: ");

        return desserts.get(selectedDessertIndex);
    }

    private void writeAvailableCuisines(List<Cuisine> availableCuisines) {
        consoleView.printLine("Available cuisines: ");
        for (int i = 0; i < availableCuisines.size(); i++) {
            printAvailableCuisine(availableCuisines, i);
        }
    }

    private void printAvailableCuisine(List<Cuisine> availableCuisines, int index) {
        Cuisine cuisine = availableCuisines.get(index);
        String cuisineDisplayName = cuisine.getDisplayName();
        consoleView.printLine(index + ". " + cuisineDisplayName);
    }

}
