package org.example.cli.controller.command;

import org.example.cli.view.ConsoleView;
import org.example.cli.view.InputReader;
import org.example.model.Drink;
import org.example.service.DrinkService;
import org.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderDrinkCommandTest {

    private DrinkService drinkService;
    private OrderService orderService;
    private InputReader inputReader;
    private OrderDrinkCommand orderDrinkCommand;

    @BeforeEach
    void setUp() {
        drinkService = mock(DrinkService.class);
        orderService = mock(OrderService.class);
        inputReader = mock(InputReader.class);
        ConsoleView consoleView = mock(ConsoleView.class);

        orderDrinkCommand = new OrderDrinkCommand(drinkService, orderService, inputReader, consoleView);
    }

    @Test
    void shouldOrderDrinkWithAdditions() {
        // given:
        Drink cola = new Drink("Cola", 6.0);
        Drink water = new Drink("Water", 4.0);
        when(drinkService.getAll()).thenReturn(List.of(cola, water));
        when(inputReader.readValidIndex(anyString(), anyInt())).thenReturn(0);
        when(inputReader.readYesNo("Do you want to add ice?")).thenReturn(true);
        when(inputReader.readYesNo("Do you want to add lemon?")).thenReturn(false);

        // when:
        boolean result = orderDrinkCommand.execute();

        // then:
        ArgumentCaptor<Drink> drinkCaptor = ArgumentCaptor.forClass(Drink.class);
        verify(orderService).addDrink(drinkCaptor.capture());
        Drink orderedDrink = drinkCaptor.getValue();
        assertEquals("Cola", orderedDrink.getName());
        assertTrue(orderedDrink.isWithIce());
        assertFalse(orderedDrink.isWithLemon());
        assertTrue(result);
    }

}
