package org.example.model;

public class Drink implements MenuItem {

    private final String name;
    private final double price;
    private boolean withIce;
    private boolean withLemon;

    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
        this.withIce = false;
        this.withLemon = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getNameWithAdditions() {
        String iceText = getIceAdditionToName();
        String lemonText = getLemonAdditionToName();

        return String.format("%s (%s, %s)", name, iceText, lemonText);
    }

    public boolean isWithIce() {
        return withIce;
    }

    public boolean isWithLemon() {
        return withLemon;
    }

    public void setWithIce(boolean withIce) {
        this.withIce = withIce;
    }

    public void setWithLemon(boolean withLemon) {
        this.withLemon = withLemon;
    }

    private String getIceAdditionToName() {
        if (withIce) {
            return "with ice";
        }

        return "without ice";
    }

    private String getLemonAdditionToName() {
        if (withLemon) {
            return "with lemon";
        }

        return "without lemon";
    }

}