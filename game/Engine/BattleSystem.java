
package Engine;

import Console.ConsoleUI;
import characters.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleSystem {
    private final RewardManager rewardManager;
    private final Random random;

    public BattleSystem() {
        rewardManager = new RewardManager();
        random = new Random();
    }

    // Supports existing battles against one enemy.
    public boolean startBattle(
            Party party,
            Enemy enemy,
            CombatActionProvider actions) {

        if (enemy == null) {
            System.out.println("Cannot start this battle.");
            return false;
        }

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        return startBattle(party, enemies, actions);
    }

    // Supports battles against multiple enemies.
    public boolean startBattle(
            Party party,
            List<Enemy> enemies,
            CombatActionProvider actions) {

        if (party == null || enemies == null || actions == null
                || !party.hasLivingMembers()) {
            System.out.println("Cannot start this battle.");
            return false;
        }

        // Make a copy so the caller's list is not modified.
        List<Enemy> battleEnemies = new ArrayList<>();

        for (Enemy enemy : enemies) {
            if (enemy != null && enemy.isAlive()) {
                battleEnemies.add(enemy);
            }
        }

        if (battleEnemies.isEmpty()) {
            System.out.println("Cannot start this battle.");
            return false;
        }

        System.out.println("\nA battle begins!");
        displayEnemies(battleEnemies);

        // Continue until one side has no living members.
        while (party.hasLivingMembers() && hasLivingEnemies(battleEnemies)) {

            // PARTY TURN
            System.out.println("\n--- Party Turn ---");

            for (GameCharacter slinger :
                    party.getLivingMembers()) {

                if (!slinger.isAlive()) {
                    continue;
                }

                int action;
                Enemy target = null;

                // Allow the player to view party status without
                // consuming the character's turn.
                while (true) {

                    action = actions.chooseAction(slinger, null);

                    if (action == 6) {
                        System.out.println(
                                "\n========== PARTY STATUS =========="
                        );

                        party.displayParty();

                        int statusChoice = actions.chooseStatusOption();

                        if (statusChoice == 1) {
                            party.getInventory().displayInventory();
                        }

                        continue;
                    }

                    else if (action == 7) {

                        int itemIndex = actions.chooseInventoryItem(
                                party.getInventory()
                        );

                        if (itemIndex == -1) {
                            continue;
                        }

                        GameCharacter itemTarget = actions.chooseItemTarget(party);

                        if (itemTarget == null) {
                            continue;
                        }

                        boolean used = party.getInventory().usePotion(
                                itemIndex,
                                itemTarget
                        );

                        if (!used) {
                            continue;
                        }
                    }

                    boolean needsEnemy =
                            action == 1
                                    || (action == 2
                                    && (slinger instanceof Gunner
                                    || slinger instanceof Shotgun
                                    || slinger instanceof TriggerHappy));

                    if (needsEnemy) {
                        List<Enemy> livingEnemies =
                                getLivingEnemies(battleEnemies);

                        if (livingEnemies.isEmpty()) {
                            break;
                        }


                        // Let the player select a living enemy first.
                        target = actions.chooseEnemy(livingEnemies);

                        if (target == null || !target.isAlive()) {
                            System.out.println("Invalid enemy target. Try again.");
                            continue;
                        }
                    }

                    // Perform the selected action.
                    if (action == 1 && target != null) {
                        slinger.shootingGun(target);

                    } else if (action == 2
                            && slinger instanceof Gunner && target != null) {
                        ((Gunner) slinger).rapidFire(target);

                    } else if (action == 2
                            && slinger instanceof Shotgun && target != null) {
                        ((Shotgun) slinger).PumpingShotgun(target);

                    } else if (action == 2
                            && slinger instanceof TriggerHappy && target != null) {
                        ((TriggerHappy) slinger).sprayAndPray(target);

                    } else if (action == 2
                            && slinger instanceof Sawbone) {
                        GameCharacter ally = actions.chooseAlly(party);

                        if (ally != null) {
                            ((Sawbone) slinger).healAlly(ally);
                        }

                    } else if (action == 3
                            && slinger instanceof Sawbone) {
                        ((Sawbone) slinger).groupHeal(
                                party.getLivingMembers());

                    } else if (action == 3
                            && slinger instanceof Gunner) {
                        ((Gunner) slinger).reload();

                    } else if (action == 3
                            && slinger instanceof Shotgun) {
                        ((Shotgun) slinger).reload();

                    } else if (action == 3
                            && slinger instanceof TriggerHappy) {
                        ((TriggerHappy) slinger).reload();

                    } else if (action == 4
                            && slinger instanceof Sawbone) {
                        ((Sawbone) slinger).recoverMeds();

                    } else if (action == 5) {
                        slinger.setDodging(true);
                        System.out.println(
                                slinger.getName()
                                        + " prepares to dodge!");

                    } else {
                        System.out.println(
                                "Invalid action. You lose this turn.");
                        continue;
                    }
                    break;
                }

                if (!party.hasLivingMembers()
                        || !hasLivingEnemies(battleEnemies)) {
                    break;
                }
            }
            if (!party.hasLivingMembers()
                    || !hasLivingEnemies(battleEnemies)) {
                break;
            }
            // ENEMY TURN: each living enemy attacks once.
            System.out.println("\n--- Enemy Turn ---");

            for (Enemy enemy :
                    new ArrayList<>(battleEnemies)) {

                if (!enemy.isAlive()
                        || !party.hasLivingMembers()) {
                    continue;
                }

// Process burning before the enemy attacks.
                if (enemy.isBurning()) {
                    enemy.processBurn();
                }

// Burning might defeat the enemy.
                if (!enemy.isAlive()) {
                    continue;
                }

                List<GameCharacter> livingMembers =
                        party.getLivingMembers();

                GameCharacter target = livingMembers.get(
                        random.nextInt(livingMembers.size()));

                System.out.println(
                        "\n" + enemy.getName() + " attacks!");

                enemy.attackTarget(target);

                if (!target.isAlive()) {
                    System.out.println(
                            target.getName()
                                    + " has been knocked down!");
                }
            }
        }

            // CHECK DEFEAT
            if (!party.hasLivingMembers()) {
                System.out.println("\nYour party has been defeated!");
                return false;
            }

            // VICTORY: award rewards for the defeated enemies.
            System.out.println("\nVictory! You defeated all enemies!");

            for (Enemy enemy : battleEnemies) {
                rewardManager.awardBattleRewards(party, enemy);
            }

            return true;
            }

    private boolean hasLivingEnemies(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                return true;
            }
        }

        return false;
    }

    private List<Enemy> getLivingEnemies(List<Enemy> enemies) {
        List<Enemy> livingEnemies = new ArrayList<>();

        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                livingEnemies.add(enemy);
            }
        }

        return livingEnemies;
    }

    private void displayEnemies(List<Enemy> enemies) {
        System.out.println("\n========== ENEMY PARTY ==========");

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            System.out.println(
                    (i + 1) + ". " + enemy.getName()
                            + " - HP: " + enemy.getHp()
                            + "/" + enemy.getMaxhp());
        }
    }

    // Compatibility overload for calls without a console action provider.
    public boolean startBattle(Party party, Enemy enemy) {
        System.out.println(
                "A combat action provider is required for this battle.");
        return false;
    }
}

