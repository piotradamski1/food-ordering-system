package org.example.model;

public class MainCourse implements MenuItem {

    private final String name;
    private final double price;
    private final Cuisine cuisine;

    public MainCourse(String name, double price, Cuisine cuisine) {
        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

}