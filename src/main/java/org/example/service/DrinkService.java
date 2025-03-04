package org.example.service;

import org.example.model.Drink;
import org.example.repository.DrinkRepository;

import java.util.List;

public class DrinkService {

    private final DrinkRepository repository;

    public DrinkService(DrinkRepository repository) {
        this.repository = repository;
    }

    public List<Drink> getAll() {
        return repository.findAll();
    }

}
