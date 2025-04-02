package org.example;

import org.example.cli.CliRunner;
import org.example.repository.DessertRepository;
import org.example.repository.DrinkRepository;
import org.example.repository.MainCourseRepository;
import org.example.service.DessertService;
import org.example.service.DrinkService;
import org.example.service.MainCourseService;
import org.example.service.OrderService;

public class Main {
    public static void main(String[] args) {
        MainCourseRepository mainCourseRepository = new MainCourseRepository();
        MainCourseService mainCourseService = new MainCourseService(mainCourseRepository);
        DessertRepository dessertRepository = new DessertRepository();
        DessertService dessertService = new DessertService(dessertRepository);
        DrinkRepository drinkRepository = new DrinkRepository();
        DrinkService drinkService = new DrinkService(drinkRepository);
        OrderService orderService = new OrderService();

        CliRunner cliRunner = new CliRunner(mainCourseService, dessertService, drinkService, orderService);
        cliRunner.run();
    }
}