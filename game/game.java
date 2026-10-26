//import characters.Gunner;
//import characters.Shotgun;
//import characters.TriggerHappy;
//import characters.Sawbone;
////import characters.Enemy;
////import characters.Bandit;
//import Engine.GameEngine;
//import Engine.BattleSystem;
//import Engine.Party;
//import Engine.RewardManager;
//import Console.ConsoleUI;
//import Console.InputHandler;
//import Items.Inventory;
//import Items.Equipment;
//import Items.Weapon;
//import Items.Armor;
//import Items.Item;
//import Items.Potion;
//import World.Location;
//import World.Bounty;
//import World.Chapter;
//import World.Map;
//import World.Shop;
//
//import java.util.Scanner;
//
//public class game {
//    public static void main(String[] args) {
////        GameCharacter lucky = new Gunner("Lucky");
////        GameCharacter shotty = new Shotgun("Shona/Shotty");
////        GameCharacter goldy = new TriggerHappy("Goldy");
////        GameCharacter sasha = new Sawbone("Sasha");
////        System.out.println(lucky.getStatus());
////        System.out.println(shotty.getStatus());
////        System.out.println(goldy.getStatus());
////        System.out.println(sasha.getStatus());
////        Location forest = new Location(
////                "Dusted Deserts",
////                "A corrupted deserts filled with hostile bandits.",
////                false
////        );
////
////        Location village = new Location(
////                "Oasis Town",
////                "A safe town where heroes can rest.",
////                true
////        );
////
////        Map map = new Map();
////        map.addLocation(village);
////        map.addLocation(forest);
////        map.travelTo("Dusted Deserts");
////        map.displayMap();
////
////        Bounty bounty = new Bounty(
////                "Clear the Deserts",
////                "Defeat five Bandits.",
////                5,
////                100,
////                50
////        );
////
////        bounty.addProgress(5);
////        bounty.claimRewards();
////
////        Chapter chapter = new Chapter(
////                1,
////                "The Dusted Deserts",
////                "The influence of the big boss is all around."
////        );
////
////        chapter.addBounty(bounty);
////        chapter.displayChapter();
////        chapter.completeChapter();
////
////        Inventory inventory = new Inventory();
////        Shop shop = new Shop("Oasis Saloon/Gun Store");
////
////        shop.addStock(new Weapon(
////                "Big Iron", "A sturdy iron revolver.", 50, 8
////        ));
////
////        int[] gold = {100};
////
////        shop.displayStock();
////        shop.buyItem(0, inventory, gold);
////
////        System.out.println("Remaining gold: " + gold[0]);
////        inventory.displayInventory()
//            Scanner scanner = new Scanner(System.in);
//
//            GameEngine game = new GameEngine();
//
//            // Create the player's party.
//            game.getParty().addMember(new Shotgun("Thorin"));
//            game.getParty().addMember(new TriggerHappy("Elara"));
//            game.getParty().addMember(new Gunner("Robin"));
//            game.getParty().addMember(new Sawbone("Luna"));
//
//            // Create the starting locations.
//            game.getGameMap().addLocation(
//                    new Location(
//                            "Eldoria Village",
//                            "A peaceful village where adventurers prepare for their journeys.",
//                            true
//                    )
//            );
//
//            game.getGameMap().addLocation(
//                    new Location(
//                            "Blighted Forest",
//                            "A dark forest filled with dangerous creatures.",
//                            false
//                    )
//            );
//
//            // Create the first chapter and quest.
//            Chapter firstChapter = new Chapter(
//                    1,
//                    "The Blighted Forest",
//                    "A mysterious corruption threatens the kingdom of Eldoria."
//            );
//
//            Bounty firstQuest = new Bounty(
//                    "Defeat the Goblin",
//                    "Defeat the goblin threatening the forest.",
//                    1,
//                    100,
//                    50
//            );
//
//            firstChapter.addBounty(firstQuest);
//            game.addChapter(firstChapter);
//
//            // Start the console interface.
//            ConsoleUI console = new ConsoleUI(game, scanner);
//            console.start();
//
//            scanner.close();
//        }
//
//    }