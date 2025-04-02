package org.example.cli.view;

import org.example.model.MenuItem;

import java.util.List;

public class ConsoleView {

    public void printWelcome() {
        printLine("======================================");
        printLine("  Welcome to the Food Ordering System ");
        printLine("======================================");
    }

    public void printActions() {
        printLine("\nSELECT ACTION:");
        printLine(" [0] Order Lunch");
        printLine(" [1] Order Drink");
        printLine(" [2] Show Summary and Exit");
    }

    public void writeMenuItems(List<? extends MenuItem> menuItems) {
        printLine("Available items: ");
        for (int i = 0; i < menuItems.size(); i++) {
            printMenuItem(menuItems, i);
        }
    }

    public void printLine(String line) {
        System.out.println(line);
    }

    private void printMenuItem(List<? extends MenuItem> menuItems, int index) {
        MenuItem menuItem = menuItems.get(index);
        String displayLine = buildMenuItemLine(menuItem, index);
        printLine(displayLine);
    }

    private String buildMenuItemLine(MenuItem menuItem, int index) {
        String name = menuItem.getName();
        double price = menuItem.getPrice();

        return String.format("%d. %s (%.2f)", index, name, price);
    }

}
