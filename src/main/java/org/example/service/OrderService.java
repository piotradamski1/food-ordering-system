package org.example.service;

import org.example.model.Drink;
import org.example.model.Lunch;
import org.example.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final List<Lunch> lunches = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();

    public void addLunch(Lunch lunch) {
        lunches.add(lunch);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public String getOrderSummary() {
        String orderedItemsNames = getOrderedItemsNames();
        double totalPrice = calculateTotalPrice();
        String totalPriceToDisplay = "Total: " + totalPrice;

        return StringUtils.joinWithNewLine(orderedItemsNames, totalPriceToDisplay);
    }

    private String getOrderedItemsNames() {
        String lunchesNames = getLunchesNames();
        String drinksNames = getDrinksNames();

        return StringUtils.joinWithNewLine(lunchesNames, drinksNames);
    }

    private String getLunchesNames() {
        return lunches.stream()
                .map(Lunch::getName)
                .collect(Collectors.joining("\n"));
    }

    private String getDrinksNames() {
        return drinks.stream()
                .map(Drink::getNameWithAdditions)
                .collect(Collectors.joining("\n"));
    }

    private double calculateTotalPrice() {
        double lunchPrice = calculateLunchTotalPrice();
        double drinkPrice = calculateDrinkTotalPrice();

        return lunchPrice + drinkPrice;
    }

    private double calculateLunchTotalPrice() {
        return lunches.stream()
                .mapToDouble(Lunch::getTotalPrice)
                .sum();
    }

    private double calculateDrinkTotalPrice() {
        return drinks.stream()
                .mapToDouble(Drink::getPrice)
                .sum();
    }

}
