package Engine;

import characters.*;
import World.Chapter;
import World.Map;
import World.Bounty;
import World.Location;
import World.Shop;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final Party party;
    private final Map gameMap;
    private final BattleSystem battleSystem;
    private final List<Chapter> chapters;
    private Shop shop;


    private int currentChapterIndex;

    public GameEngine() {
        party = new Party();
        gameMap = new Map();
        battleSystem = new BattleSystem();
        chapters = new ArrayList<>();
        currentChapterIndex = 0;
        shop = new Shop("Oasis Town General Store");
    }

    public Party getParty() {

        return party;
    }

    public Map getGameMap() {

        return gameMap;
    }
    public Shop getShop() {

        return shop;
    }

    public Location getCurrentLocation() {
        return gameMap.getCurrentLocation();
    }

    public Chapter getCurrentChapter() {
        if (currentChapterIndex < 0
                || currentChapterIndex >= chapters.size()) {
            return null;
        }

        return chapters.get(currentChapterIndex);
    }

    public void addChapter(Chapter chapter) {
        if (chapter != null) {
            chapters.add(chapter);
        }
    }

    // Interactive version: asks the player to choose each action.

    // Interactive battle against one enemy.
    public boolean startBattle(
            Enemy enemy,
            CombatActionProvider actions) {

        if (enemy == null) {
            System.out.println("Cannot start this battle.");
            return false;
        }

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        return startBattle(enemies, actions);
    }

    // Interactive battle against multiple enemies.
    public boolean startBattle(
            List<Enemy> enemies,
            CombatActionProvider actions) {

        if (enemies == null) {
            System.out.println("Cannot start this battle.");
            return false;
        }


        // Remember which enemies were alive when battle started.
        List<Enemy> enemiesAliveAtStart = new ArrayList<>();

        for (Enemy enemy : enemies) {
            if (enemy != null && enemy.isAlive()) {
                enemiesAliveAtStart.add(enemy);
            }
        }

        boolean victory = battleSystem.startBattle(
                party, enemies, actions);

        // Give Rescue Daji progress for each defeated bandit.
        for (Enemy enemy : enemiesAliveAtStart) {
            if (enemy instanceof Bandit && !enemy.isAlive()) {
                addBountyProgress("Rescue Daji", 1);
            }
        }

        return victory;
    }


    public boolean travelTo(String locationName) {
        return gameMap.travelTo(locationName);
    }

    private void recruitCompanion(GameCharacter character) {
        if (character != null
                && !party.hasMember(character.getName())) {
            party.addMember(character);
        }
    }


    public boolean completeCurrentChapter() {
        Chapter current = getCurrentChapter();

        if (current == null) {
            return false;
        }

        if (!current.completeChapter()) {
            System.out.println(
                    "Complete all chapter quests before proceeding."
            );
            return false;
        }


        // Recruit companions based on the completed chapter.
        switch (current.getChapterNumber()) {
            case 1:
                recruitCompanion(new Gunner("Rook"));
                break;

            case 2:
                // Replace Medic with your actual support character class.
                // recruitCompanion(new Medic("Mara"));
                break;

            case 3:
                // Add another companion when you create their class.
                // recruitCompanion(new SomeCharacter("Name"));
                break;

            default:
                break;
        }

        if (currentChapterIndex + 1 < chapters.size()) {
            currentChapterIndex++;
            chapters.get(currentChapterIndex).unlock();

            System.out.println(
                    "The next chapter has been unlocked!"
            );
        } else {
            System.out.println("You completed the campaign!");
        }

        return true;
    }

    public boolean addBountyProgress(String bountyName, int amount) {
        if (bountyName == null || amount <= 0) {
            return false;
        }

        // Search every chapter, not just the current chapter.
        for (Chapter chapter : chapters) {
            for (Bounty bounty : chapter.getBounty()) {

                if (bounty.getName().equalsIgnoreCase(bountyName)) {
                    boolean wasCompleted = bounty.isCompleted();

                    bounty.addProgress(amount);

                    // Trigger recruitment only when the bounty first completes.
                    if (!wasCompleted && bounty.isCompleted()) {
                        if (bounty.getName().equalsIgnoreCase("Rescue Daji")) {
                            System.out.println("You rescued Daji!");
                            recruitCompanion(new Daji("Daji"));
                        }

                        System.out.println(
                                "Bounty completed: " + bounty.getName()
                        );
                    }

                    return true;
                }
            }
        }

        System.out.println("Bounty not found: " + bountyName);
        return false;
    }


    public void displayGameStatus() {
        Chapter current = getCurrentChapter();

        if (current != null) {
            current.displayChapter();
        } else {
            System.out.println("No chapter is currently available.");
        }

        party.displayParty();
        gameMap.displayMap();
    }


    public void displayBountyProgress(String bountyName) {
        for (Chapter chapter : chapters) {
            for (Bounty bounty : chapter.getBounty()) {
                if (bounty.getName().equalsIgnoreCase(bountyName)) {
                    System.out.println(
                            bounty.getName() + ": "
                                    + bounty.getCurrentProgress() + "/"
                                    + bounty.getRequiredProgress()
                    );
                    return;
                }
            }
        }

        System.out.println("Bounty not found: " + bountyName);
    }

}