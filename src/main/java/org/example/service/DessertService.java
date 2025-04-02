package org.example.service;

import org.example.model.Dessert;
import org.example.repository.DessertRepository;

import java.util.List;

public class DessertService {

    private final DessertRepository repository;

    public DessertService(DessertRepository  repository) {
        this.repository = repository;
    }

    public List<Dessert> getAll() {
        return repository.findAll();
    }

}
