package org.example.repository;

import org.example.model.Cuisine;
import org.example.model.MainCourse;

import java.util.ArrayList;
import java.util.List;

public class MainCourseRepository implements BaseRepository<MainCourse> {

    private final List<MainCourse> mainCourses = new ArrayList<>();

    public MainCourseRepository() {
        mockData();
    }

    @Override
    public List<MainCourse> findAll() {
        return mainCourses;
    }

    public List<Cuisine> findAllAvailableCuisines() {
        return mainCourses.stream()
                .map(MainCourse::getCuisine)
                .distinct()
                .sorted()
                .toList();
    }

    public List<MainCourse> findByCuisine(Cuisine cuisine) {
        return mainCourses.stream()
                .filter(dish -> isCuisineMatch(dish, cuisine))
                .toList();
    }

    private void mockData() {
        add("Schabowy", 20, Cuisine.POLISH);
        add("Bigos", 22, Cuisine.POLISH);
        add("Tacos", 25, Cuisine.MEXICAN);
        add("Burrito", 28, Cuisine.MEXICAN);
        add("Spaghetti", 26, Cuisine.ITALIAN);
        add("Pizza", 30, Cuisine.ITALIAN);
    }

    private void add(String name, double price, Cuisine cuisine) {
        MainCourse mainCourse = new MainCourse(name, price, cuisine);
        mainCourses.add(mainCourse);
    }

    private boolean isCuisineMatch(MainCourse dish, Cuisine cuisine) {
        Cuisine dishCuisine = dish.getCuisine();
        return dishCuisine.equals(cuisine);
    }

}
