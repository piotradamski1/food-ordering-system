package org.example.repository;

import org.example.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkRepository implements BaseRepository<Drink> {

    private final List<Drink> drinks = new ArrayList<>();

    public DrinkRepository () {
        mockData();
    }

    @Override
    public List<Drink> findAll() {
        return drinks;
    }

    private void mockData() {
        add("Water", 5.0);
        add("Cola", 6.0);
    }

    private void add(String name, double price) {
        Drink drink = new Drink(name, price);
        drinks.add(drink);
    }

}
