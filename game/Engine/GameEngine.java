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

    // Non-interactive version retained for compatibility.
    public boolean startBattle(Enemy enemy) {
        System.out.println(
                "Start this battle through the console menu."
        );
        return false;
    }

    // Interactive version: asks the player to choose each action.
    public boolean startBattle(
            Enemy enemy,
            CombatActionProvider actions) {

        boolean victory = battleSystem.startBattle(
                party, enemy, actions
        );

        if (victory) {
            System.out.println("Battle completed successfully!");
        }

        return victory;
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
        System.out.println("\n=== SHADOWS OF ELDORIA ===");

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