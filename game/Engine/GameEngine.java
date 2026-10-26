package Engine;

import characters.Enemy;
import World.Chapter;
import World.Map;
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

    public boolean startBattle(Enemy enemy) {
        boolean victory = battleSystem.startBattle(party, enemy);

        if (victory) {
            System.out.println(
                    "You can now continue your adventure.");
        }

        return victory;
    }

    public boolean travelTo(String locationName) {
        return gameMap.travelTo(locationName);
    }

    public boolean completeCurrentChapter() {
        Chapter current = getCurrentChapter();

        if (current == null) {
            System.out.println("There is no current chapter.");
            return false;
        }

        if (!current.completeChapter()) {
            return false;
        }

        if (currentChapterIndex + 1 < chapters.size()) {
            currentChapterIndex++;

            Chapter next = getCurrentChapter();
            next.unlock();

            System.out.println("Your next destination is: "
                    + next.getTitle());
        } else {
            System.out.println(
                    "Congratulations! You completed the campaign!");
        }

        return true;
    }

    public void displayGameStatus() {
        System.out.println("\n=== SHADOWS OF ELDORIA ===");

        Chapter current = getCurrentChapter();

        if (current != null) {
            System.out.println("Current chapter: "
                    + current.getTitle());
        } else {
            System.out.println("No chapter has been added.");
        }

        party.displayParty();
        gameMap.displayMap();
    }
}

