package Console;

import characters.Gunner;
import characters.Shotgun;
import characters.TriggerHappy;
import characters.Sawbone;
import Engine.GameEngine;
import World.Chapter;
import World.Location;
import World.Bounty;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameEngine game = new GameEngine();

        // Create the player's party.
        game.getParty().addMember(new Gunner("Thorin"));
        game.getParty().addMember(new Shotgun("Elara"));
        game.getParty().addMember(new TriggerHappy("Robin"));
        game.getParty().addMember(new Sawbone("Luna"));

        // Create the starting locations.
        game.getGameMap().addLocation(
                new Location(
                        "Eldoria Village",
                        "A peaceful village where adventurers prepare for their journeys.",
                        true
                )
        );

        game.getGameMap().addLocation(
                new Location(
                        "Blighted Forest",
                        "A dark forest filled with dangerous creatures.",
                        false
                )
        );

        // Create the first chapter and quest.
        Chapter firstChapter = new Chapter(
                1,
                "The Blighted Forest",
                "A mysterious corruption threatens the kingdom of Eldoria."
        );

        Bounty firstQuest = new Bounty(
                "Defeat the Goblin",
                "Defeat the goblin threatening the forest.",
                1,
                100,
                50
        );

        firstChapter.addBounty(firstQuest);
        game.addChapter(firstChapter);

        // Start the console interface.
        ConsoleUI console = new ConsoleUI(game, scanner);
        console.start();

        scanner.close();
    }
}