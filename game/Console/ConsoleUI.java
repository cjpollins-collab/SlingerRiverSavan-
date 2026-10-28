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
import World.Location;

import java.util.ArrayList;
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
                    showBountyList();
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
        System.out.println("       SLINGER RIVER SAVAN");
        System.out.println("==================================");
        System.out.println(
                "Corruption plagues this land."
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
        System.out.println("3. View Bounty List");
        System.out.println("4. View the World Map");
        System.out.println("5. Quit Game");
        System.out.println("===============================");
    }

    public void showStatus() {
        System.out.println("\n========== PARTY STATUS ==========");
        game.getParty().displayParty();
    }

    private void travel() {
        System.out.println("\n========== TRAVEL ==========");
        System.out.println("Enter a location name.");
        System.out.println("Examples: Oasis Town, Dusted Deserts");

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
    private void startBanditBattle() {
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


    private void startGroupBanditBattle() {
        System.out.println("\nSeveral bandits surround your party!");

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Bandit());
        enemies.add(new Bandit());
        enemies.add(new BanditLeader());

        boolean victory = game.startBattle(enemies, this);

        if (victory) {
            System.out.println(
                    "Your party survived the bandit ambush.");
        } else {
            System.out.println(
                    "Your party was unable to survive the ambush.");
        }
    }


    private void showBountyList() {
        Location location = game.getCurrentLocation();

        System.out.println("\n========== BOUNTY BOARD ==========");

        if (location == null) {
            System.out.println("No location is currently available.");
            return;
        }

        System.out.println("Location: " + location.getName());

        if (location.isSafeZone()) {
            System.out.println("This is a safe zone. No bounties available.");
            return;
        }

        if (location.getName().equalsIgnoreCase("Dusted Deserts")) {
            System.out.println("1. Forest Bandit - 50 coins");
            System.out.println("2. Corrupted Outlaw - 100 coins");
            System.out.println("3. Bandit Ambush - Group Battle");
            System.out.println("4. Return to Main Menu");

            int choice = input.readInt("Choose a bounty: ", 1, 4);

            switch (choice) {
                case 1:
                    startBanditBattle();
                    break;
                case 2:
                    startBossBattle();
                    break;
                case 3:
                    startGroupBanditBattle();
                case 4:
                    return;
            }

        } else if (location.getName().equalsIgnoreCase("Dusty Trail")) {
            System.out.println("1. Road Bandit - 50 coins");
            System.out.println("2. Return to Main Menu");

            int choice = input.readInt("Choose a bounty: ", 1, 2);

            if (choice == 1) {
                startBanditBattle();
            }

        } else {
            System.out.println("No bounties are available here yet.");
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
                    "Thank you for playing Slinger River Savan!"
            );
        } else {
            System.out.println("Returning to the main menu.");
        }
    }

    // Display actions available to the current character.
    @Override
    public int chooseAction(GameCharacter slinger, Enemy enemy) {

        System.out.println(
                "\n--- " + slinger.getName() + "'s Turn ---"
        );

        System.out.println(
                "HP: " + slinger.getHp()
                        + "/" + slinger.getMaxhp()
        );
        if (slinger instanceof Slinger) {
            System.out.println(
                    ((Slinger) slinger).getAmmoStatus()
            );
        }

        System.out.println("\n========== COMBAT ACTIONS ==========");

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

        System.out.println("5. Dodge");
        System.out.println("6. View Party Status");

        return input.readInt("Choose an action: ", 1, 8);
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

    @Override
    public Enemy chooseEnemy(List<Enemy> enemies) {
        System.out.println("\n========== ENEMY PARTY ==========");

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            System.out.println((i + 1) + ". " + enemies.get(i).getName()+ " - HP: " + enemy.getHp()
                    + "/" + enemy.getMaxhp());
        }

        int choice = input.readInt(
                "Choose an enemy to target: ", 1, enemies.size());

        return enemies.get(choice - 1);
    }
}