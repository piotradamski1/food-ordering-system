package org.example.service;

import org.example.model.Cuisine;
import org.example.model.MainCourse;
import org.example.repository.MainCourseRepository;

import java.util.List;

public class MainCourseService {

    private final MainCourseRepository repository;

    public MainCourseService(MainCourseRepository repository) {
        this.repository = repository;
    }

    public List<MainCourse> getAll() {
        return repository.findAll();
    }

    public List<MainCourse> getByCuisine(Cuisine cuisine) {
        return repository.findByCuisine(cuisine);
    }

    public List<Cuisine> getAllAvailableCuisines() {
        return repository.findAllAvailableCuisines();
    }

}
