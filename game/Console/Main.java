package Console;

import Console.ConsoleUI;
import Items.Armor;
import Items.Potion;
import Items.Weapon;
import Items.WeaponEffect;
import World.Shop;
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

        Bounty secondQuest = new Bounty(
                "Bring in the Leader",
                "The leader has intel of where to find Daji, Bring them in.",
                1,75,100
        );

        Bounty rescueDaji = new Bounty(
                "Rescue Daji",
                "Find Daji and rescue him from the outlaws.",
                3,
                150,
                200
        );

        Weapon kickback = new Weapon(
                "Kickback",
                "Doubles ability damage.",
                40, 0);
        kickback.addEffect(WeaponEffect.DOUBLE_ABILITY_DAMAGE);

        Weapon hellfire = new Weapon(
                "Hellfire",
                "Increases critical hit chance while burning enemies.",
                50, 5);
        hellfire.addEffect(WeaponEffect.BURN_ENEMY);
        hellfire.addEffect(WeaponEffect.INCREASE_CRITICAL_CHANCE);

        Weapon vampire = new Weapon(
                "Vampire",
                "Restores ammo after a kill.",
                60, 3);

        Potion healthPotion = new Potion(
                "Health Potion",
                "Restores 50 HP to a party member.",
                30,
                Potion.PotionType.HEALTH,
                50
        );

        Potion medicalPotion = new Potion(
                "Medical Supplies",
                "Restores Sawbone's medical supplies.",
                40,
                Potion.PotionType.MEDICAL,
                1
        );



        firstChapter.addBounty(rescueDaji);
        Shop shop = game.getShop();
        Armor cloth = new Armor("Cloth","Allows user to tank more shots",15,20 );
        Weapon iron = new Weapon("Big Iron", "Adds a ton of Damage",40,30);
        shop.addStock(cloth);
        shop.addStock(kickback);
        shop.addStock(hellfire);
        shop.addStock(vampire);
        shop.addStock(iron);
        shop.addStock(healthPotion);
        shop.addStock(medicalPotion);
// Add items to the shop here.

        firstChapter.addBounty(firstQuest);
        game.addChapter(firstChapter);

        // Start the console interface.
        ConsoleUI console = new ConsoleUI(game, scanner);
        console.start();

        scanner.close();
    }
}