package Console;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.scanner = scanner;
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(input);

                if (number >= min && number <= max) {
                    return number;
                }

                System.out.println(
                        "Please enter a number from " + min + " to " + max + "."
                );
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            String answer = readLine(prompt);

            if (answer.equalsIgnoreCase("y")
                    || answer.equalsIgnoreCase("yes")) {
                return true;
            }

            if (answer.equalsIgnoreCase("n")
                    || answer.equalsIgnoreCase("no")) {
                return false;
            }

            System.out.println("Please enter yes or no.");
        }
    }
}