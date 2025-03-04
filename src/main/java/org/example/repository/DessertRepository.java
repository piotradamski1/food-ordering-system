package org.example.repository;

import org.example.model.Dessert;

import java.util.ArrayList;
import java.util.List;

public class DessertRepository implements BaseRepository<Dessert> {

    private final List<Dessert> desserts = new ArrayList<>();

    public DessertRepository() {
        mockData();
    }

    @Override
    public List<Dessert> findAll() {
        return desserts;
    }

    private void mockData() {
        add("Sernik", 10);
        add("Tiramisu", 12);
        add("Jabłecznik", 9);
    }

    private void add(String name, double price) {
        Dessert dessert = new Dessert(name, price);
        desserts.add(dessert);
    }

}
