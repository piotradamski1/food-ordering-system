package org.example.cli.controller.command;

import org.example.cli.view.ConsoleView;
import org.example.cli.view.InputReader;
import org.example.model.Cuisine;
import org.example.model.Dessert;
import org.example.model.Lunch;
import org.example.model.MainCourse;
import org.example.service.DessertService;
import org.example.service.MainCourseService;
import org.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderLunchCommandTest {

    private MainCourseService mainCourseService;
    private DessertService dessertService;
    private OrderService orderService;
    private InputReader inputReader;
    private OrderLunchCommand orderLunchCommand;

    @BeforeEach
    void setUp() {
        mainCourseService = mock(MainCourseService.class);
        dessertService = mock(DessertService.class);
        orderService = mock(OrderService.class);
        inputReader = mock(InputReader.class);
        ConsoleView consoleView = mock(ConsoleView.class);

        orderLunchCommand = new OrderLunchCommand(mainCourseService, dessertService, orderService, inputReader, consoleView);
    }

    @Test
    void shouldOrderLunchWithDessert() {
        // given:
        when(mainCourseService.getAllAvailableCuisines()).thenReturn(List.of(Cuisine.POLISH, Cuisine.MEXICAN));
        when(inputReader.readValidIndex(anyString(), anyInt())).thenReturn(0);
        MainCourse mainCourse = new MainCourse("Schabowy", 25.0, Cuisine.POLISH);
        when(mainCourseService.getByCuisine(Cuisine.POLISH)).thenReturn(List.of(mainCourse));
        when(inputReader.readYesNo("Do you want to order dessert?")).thenReturn(true);
        Dessert dessert = new Dessert("Sernik", 12.0);
        when(dessertService.getAll()).thenReturn(List.of(dessert));
        when(inputReader.readInt("Choose Dessert: ")).thenReturn(0);

        // when:
        boolean result = orderLunchCommand.execute();

        // then:
        ArgumentCaptor<Lunch> lunchCaptor = ArgumentCaptor.forClass(Lunch.class);
        verify(orderService).addLunch(lunchCaptor.capture());
        Lunch orderedLunch = lunchCaptor.getValue();
        MainCourse orderedMainCourse = orderedLunch.getMainCourse();
        String mainCourseName = orderedMainCourse.getName();
        assertEquals("Schabowy", mainCourseName);
        Dessert orderedDessert = orderedLunch.getDessert();
        String dessertName = orderedDessert.getName();
        assertEquals("Sernik", dessertName);
        assertTrue(result);
    }

    @Test
    void shouldOrderLunchWithoutDessert() {
        // given:
        when(mainCourseService.getAllAvailableCuisines()).thenReturn(List.of(Cuisine.POLISH));
        when(inputReader.readValidIndex(anyString(), anyInt())).thenReturn(0);
        MainCourse mainCourse = new MainCourse("Pierogi", 20.0, Cuisine.POLISH);
        when(mainCourseService.getByCuisine(Cuisine.POLISH)).thenReturn(List.of(mainCourse));
        when(inputReader.readYesNo("Do you want to order dessert?")).thenReturn(false);

        // when:
        boolean result = orderLunchCommand.execute();

        // then:
        ArgumentCaptor<Lunch> lunchCaptor = ArgumentCaptor.forClass(Lunch.class);
        verify(orderService).addLunch(lunchCaptor.capture());
        Lunch orderedLunch = lunchCaptor.getValue();
        MainCourse orderedLunchMainCourse = orderedLunch.getMainCourse();
        String mainCourseName = orderedLunchMainCourse.getName();
        assertEquals("Pierogi", mainCourseName);
        assertNull(orderedLunch.getDessert());
        assertTrue(result);
    }

}
