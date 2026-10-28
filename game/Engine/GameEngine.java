package Engine;

import characters.*;
import World.Chapter;
import World.Map;
import World.Location;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final Party party;
    private final Map gameMap;
    private final BattleSystem battleSystem;
    private final List<Chapter> chapters;

    private int currentChapterIndex;

    public GameEngine() {
        party = new Party();
        gameMap = new Map();
        battleSystem = new BattleSystem();
        chapters = new ArrayList<>();
        currentChapterIndex = 0;
    }

    public Party getParty() {
        return party;
    }

    public Map getGameMap() {
        return gameMap;
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

    public boolean startBattle(
            Enemy enemy,
            CombatActionProvider actions) {
        return battleSystem.startBattle(party, enemy, actions);
    }

    public boolean startBattle(
            List<Enemy> enemies,
            CombatActionProvider actions) {
        return battleSystem.startBattle(party, enemies, actions);
    }


    public boolean travelTo(String locationName) {
        return gameMap.travelTo(locationName);
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

    public void displayGameStatus() {
        System.out.println("\n=== SLINGER RIVER SAVAN ===");

        Chapter current = getCurrentChapter();

        if (current != null) {
            current.displayChapter();
        } else {
            System.out.println("No chapter is currently available.");
        }

        party.displayParty();
        gameMap.displayMap();
    }
}