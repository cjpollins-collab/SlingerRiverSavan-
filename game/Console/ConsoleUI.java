package Console;
//import characters.Gunner;
//import characters.Shotgun;
//import characters.TriggerHappy;
//import characters.Sawbone;
//import characters.Enemy;
////import characters.Bandit;
//import Engine.GameEngine;
//import Engine.BattleSystem;
//import Engine.Party;
import Engine.CombatActionProvider;
import Engine.GameEngine;
import characters.*;
import Engine.Party;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements CombatActionProvider {

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
        input = new InputHandler(scanner);
        running = true;
    }

    public void start() {
        displayWelcome();

        while (running) {
            displayMenu();

            int choice = input.readInt(
                    "Choose an option: ", 1, 6
            );

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
                case 6:
                    startBossBattle();

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
        System.out.println(
                "Darkness threatens the kingdom."
        );
        System.out.println(
                "Gather your party and begin your journey!"
        );
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("========== MAIN MENU ==========");
        System.out.println("1. View Party Status");
        System.out.println("2. Travel to a Location");
        System.out.println("3. Fight a Bandit");
        System.out.println("4. View the World Map");
        System.out.println("5. Quit Game");
        System.out.println("===============================");
    }

    private void showStatus() {
        System.out.println("\n========== PARTY STATUS ==========");
        game.getParty().displayParty();
    }

    private void travel() {
        System.out.println("\n========== TRAVEL ==========");
        System.out.println("Enter a location name.");
        System.out.println("Examples: Eldoria Village, Blighted Forest");

        String locationName = input.readLine("Destination: ");

        if (game.travelTo(locationName)) {
            System.out.println(
                    "You traveled to " + locationName + "."
            );
        } else {
            System.out.println("That location could not be found.");
        }
    }

    private void startBossBattle() {
        System.out.println("\nA bandit approaches your party!");

        boolean victory = game.startBattle(new BanditLeader(), this);

        if (victory) {
            System.out.println(
                    "Your party survived the encounter."
            );
        } else {
            System.out.println(
                    "Your party was unable to defeat the bandit."
            );
        }
    }
    private void startGoblinBattle() {
        System.out.println("\nA bandit approaches your party!");

        boolean victory = game.startBattle(new Bandit(), this);

        if (victory) {
            System.out.println(
                    "Your party survived the encounter."
            );
        } else {
            System.out.println(
                    "Your party was unable to defeat the bandit."
            );
        }
    }

    private void showMap() {
        System.out.println("\n========== WORLD MAP ==========");
        game.getGameMap().displayMap();
    }

    private void quitGame() {
        boolean confirm = input.readYesNo(
                "Are you sure you want to quit? (y/n): "
        );

        if (confirm) {
            running = false;
            System.out.println(
                    "Thank you for playing Shadows of Eldoria!"
            );
        } else {
            System.out.println("Returning to the main menu.");
        }
    }

    // Display actions available to the current character.
    @Override
    public int chooseAction(
            GameCharacter slinger, Enemy enemy) {

        System.out.println(
                "\n--- " + slinger.getName() + "'s Turn ---"
        );

        System.out.println(
                "HP: " + slinger.getHp()
                        + "/" + slinger.getMaxhp()
        );

        System.out.println(
                "Enemy: " + enemy.getName()
                        + " (" + enemy.getHp() + " HP)"
        );

        System.out.println("1. Shoot");

        if (slinger instanceof Gunner ) {
            System.out.println("2. Rapid Fire");
            System.out.println("3. Reload");
        } else if (slinger instanceof Shotgun) {
            System.out.println("2. Double Pump");
            System.out.println("3. Reload");
        } else if (slinger instanceof TriggerHappy) {
            System.out.println("2. Spray and pray");
            System.out.println("3. Reload");
        } else if (slinger instanceof Sawbone) {
            System.out.println("2. Heal an Ally");
            System.out.println("3. Group Heal");
            System.out.println("4. Regain meds");
        }
        int maxChoice = 1;
        if (slinger instanceof Gunner || slinger instanceof Shotgun ||
                slinger instanceof TriggerHappy || slinger instanceof Sawbone) {
            maxChoice = 4;
        } else {
            maxChoice = 1; // Or 2, if a default Slinger has a second option
        }

        return input.readInt("Choose an action: ", 1, maxChoice);
    }

    // Let the player select a living party member to heal.
    @Override
    public GameCharacter chooseAlly(Party party) {
        List<GameCharacter> livingMembers =
                party.getLivingMembers();

        if (livingMembers.isEmpty()) {
            System.out.println("No living allies are available.");
            return null;
        }

        System.out.println("\nChoose an ally to heal:");

        for (int i = 0; i < livingMembers.size(); i++) {
            GameCharacter member = livingMembers.get(i);

            System.out.println(
                    (i + 1) + ". " + member.getName()
                            + " - " + member.getHp()
                            + "/" + member.getMaxhp() + " HP"
            );
        }

        int choice = input.readInt(
                "Choose a party member: ",
                1,
                livingMembers.size()
        );

        return livingMembers.get(choice - 1);
    }
}