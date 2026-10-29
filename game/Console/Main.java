package Console;

import Console.ConsoleUI;
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
        game.getParty().addMember(new Gunner("Lucky"));
        game.getParty().addMember(new Shotgun("Shotty"));
        game.getParty().addMember(new TriggerHappy("Goldy"));
        game.getParty().addMember(new Sawbone("Sasha"));

        // Create the starting locations.
        game.getGameMap().addLocation(
                new Location(
                        "Oasis Town",
                        "A peaceful village where adventurers prepare for their journeys.",
                        true
                )
        );

        game.getGameMap().addLocation(
                new Location(
                        "Dusted Deserts",
                        "A dark forest filled with dangerous creatures.",
                        false
                )
        );

        // Create the first chapter and quest.
        Chapter firstChapter = new Chapter(
                1,
                "Dusted Deserts",
                "The influence of the big boss is all around."
        );

        Bounty firstQuest = new Bounty(
                "Bring in the Bandit",
                "Defeat the Bandits.",
                1,
                100,
                50
        );

        Bounty rescueDaji = new Bounty(
                "Rescue Daji",
                "Find Daji and rescue him from the outlaws.",
                3,
                100,
                50
        );

        firstChapter.addBounty(rescueDaji);
        game.addBountyProgress("Rescue Daji", 1);

        firstChapter.addBounty(firstQuest);
        game.addChapter(firstChapter);

        // Start the console interface.
        ConsoleUI console = new ConsoleUI(game, scanner);
        console.start();

        scanner.close();
    }
}