package org.example.cli.view;

import java.util.Scanner;

public class InputReader {

    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleView view;

    public InputReader(ConsoleView view) {
        this.view = view;
    }

    public String readLine(String prompt) {
        view.printLine(prompt);
        return scanner.nextLine();
    }

    public boolean readYesNo(String prompt) {
        String input = readLine(prompt + " (y/n)");
        return input.equals("y");
    }

    public int readValidIndex(String prompt, int maxIndex) {
        while (true) {
            int index = readInt(prompt);
            if (index >= 0 && index < maxIndex) {
                return index;
            }
            view.printLine("Invalid index");
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return tryToReadInt(prompt);
            } catch (NumberFormatException ignored) {
                view.printLine("Please enter a valid integer!");
            }
        }
    }

    private int tryToReadInt(String prompt) {
        String input = readLine(prompt);
        return Integer.parseInt(input);
    }

}
