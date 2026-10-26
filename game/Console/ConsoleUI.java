package Console;

import Engine.GameEngine;
import java.util.Scanner;

public class ConsoleUI {
    private final GameEngine game;
    private final InputHandler input;
    private boolean running;

    public ConsoleUI(GameEngine game, Scanner scanner) {
        if (game == null || scanner == null) {
            throw new IllegalArgumentException(
                    "Game and Scanner cannot be null."
            );
        }

        this.game = game;
        this.input = new InputHandler(scanner);
        this.running = true;
    }

    public void start() {
        displayWelcome();

        while (running) {
            displayMenu();

            int choice = input.readInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    showStatus();
                    break;

                case 2:
                    travel();
                    break;

                case 3:
                    startGoblinBattle();
                    break;

                case 4:
                    showMap();
                    break;

                case 5:
                    quitGame();
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void displayWelcome() {
        System.out.println();
        System.out.println("==================================");
        System.out.println("       SHADOWS OF ELDORIA");
        System.out.println("==================================");
        System.out.println("Darkness threatens the kingdom.");
        System.out.println("Gather your party and begin your journey!");
        System.out.println();
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("========== MAIN MENU ==========");
        System.out.println("1. View Party Status");
        System.out.println("2. Travel to a Location");
        System.out.println("3. Fight a Goblin");
        System.out.println("4. View the World Map");
        System.out.println("5. Quit Game");
        System.out.println("===============================");
    }

    private void showStatus() {
        System.out.println();
        System.out.println("========== PARTY STATUS ==========");
        game.getParty().displayParty();
    }

    private void travel() {
        System.out.println();
        System.out.println("========== TRAVEL ==========");
        System.out.println("Enter the exact name of a discovered or known location.");
        System.out.println("Example: Eldoria Village");
        System.out.println("Example: Blighted Forest");

        String locationName = input.readLine("Destination: ");

        if (game.travelTo(locationName)) {
            System.out.println("You traveled to " + locationName + ".");
        } else {
            System.out.println("That location could not be found.");
        }
    }

    private void startGoblinBattle() {
        System.out.println();
        System.out.println("A bandit approaches your party!");

        boolean victory = game.startBattle(new Bandit());

        if (victory) {
            System.out.println("Your party survived the encounter.");
        } else {
            System.out.println("Your party was unable to defeat the goblin.");
        }
    }

    private void showMap() {
        System.out.println();
        System.out.println("========== WORLD MAP ==========");
        game.getGameMap().displayMap();
    }

    private void quitGame() {
        boolean confirm = input.readYesNo("Are you sure you want to quit? (y/n): ");

        if (confirm) {
            running = false;
            System.out.println("Thank you for playing Shadows of Eldoria!");
        } else {
            System.out.println("Returning to the main menu.");
        }
    }
}
