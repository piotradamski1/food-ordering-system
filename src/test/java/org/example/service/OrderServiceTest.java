package org.example.service;

import org.example.model.Drink;
import org.example.model.Lunch;
import org.example.model.MainCourse;
import org.example.model.Dessert;
import org.example.model.Cuisine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void shouldAddLunchToOrder() {
        // given:
        MainCourse mainCourse = new MainCourse("Schabowy", 25.0, Cuisine.POLISH);
        Dessert dessert = new Dessert("Sernik", 12.0);
        Lunch lunch = new Lunch(mainCourse, dessert);

        // when:
        orderService.addLunch(lunch);

        // then:
        String orderSummary = orderService.getOrderSummary();
        boolean containsLunch = orderSummary.contains("Schabowy");
        assertTrue(containsLunch);
        boolean containsDessert = orderSummary.contains("Sernik");
        assertTrue(containsDessert);
        boolean containsTotal = orderSummary.contains("Total: 37.0");
        assertTrue(containsTotal);
    }

    @Test
    void shouldAddDrinkToOrder() {
        // given:
        Drink drink = new Drink("Cola", 5.0);
        drink.setWithIce(true);
        drink.setWithLemon(false);

        // when:
        orderService.addDrink(drink);

        // then:
        String orderSummary = orderService.getOrderSummary();
        boolean containsCola = orderSummary.contains("Cola (with ice, without lemon)");
        assertTrue(containsCola);
        boolean containsTotal = orderSummary.contains("Total: 5.0");
        assertTrue(containsTotal);
    }

    @Test
    void shouldCalculateTotalPriceCorrectly() {
        // given:
        MainCourse mainCourse = new MainCourse("Tacos", 20.0, Cuisine.MEXICAN);
        Dessert dessert = new Dessert("Churros", 8.0);
        Lunch lunch = new Lunch(mainCourse, dessert);
        Drink drink = new Drink("Margarita", 15.0);
        drink.setWithIce(false);
        drink.setWithLemon(true);

        // when:
        orderService.addLunch(lunch);
        orderService.addDrink(drink);

        // then:
        String orderSummary = orderService.getOrderSummary();
        boolean containsTacos = orderSummary.contains("Tacos");
        assertTrue(containsTacos);
        boolean containsChurros = orderSummary.contains("Churros");
        assertTrue(containsChurros);
        boolean containsMargarita = orderSummary.contains("Margarita (without ice, with lemon)");
        assertTrue(containsMargarita);
        boolean containsTotal = orderSummary.contains("Total: 43.0");
        assertTrue(containsTotal);
    }

    @Test
    void shouldReturnEmptySummaryForNoOrders() {
        // given:
        // when:
        String orderSummary = orderService.getOrderSummary();

        // then:
        assertTrue(orderSummary.contains("Total: 0.0"));
    }

}
