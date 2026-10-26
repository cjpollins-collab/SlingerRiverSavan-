package Engine;

import characters.Enemy;
import characters.GameCharacter;
import java.util.List;
import java.util.Random;

public class BattleSystem {
    private final RewardManager rewardManager;
    private final Random random;

    public BattleSystem() {
        rewardManager = new RewardManager();
        random = new Random();
    }

    public boolean startBattle(Party party, Enemy enemy) {
        if (party == null || enemy == null
                || !party.hasLivingMembers()
                || !enemy.isAlive()) {
            System.out.println("Cannot start this battle.");
            return false;
        }

        System.out.println("\nA battle begins!");
        System.out.println("Enemy: " + enemy.getName());

        while (party.hasLivingMembers() && enemy.isAlive()) {
            // Party turn
            System.out.println("\n--- Party Turn ---");

            for (GameCharacter slinger : party.getLivingMembers()) {
                if (!enemy.isAlive()) {
                    break;
                }

                System.out.println("\n" + slinger.getName()
                        + "'s turn.");

                slinger.shootingGun(enemy);

                if (!enemy.isAlive()) {
                    System.out.println(enemy.getName()
                            + " has been defeated!");
                }
            }

            if (!enemy.isAlive()) {
                break;
            }

            // Enemy turn
            List<GameCharacter> livingHeroes =
                    party.getLivingMembers();

            if (livingHeroes.isEmpty()) {
                break;
            }

            GameCharacter target = livingHeroes.get(
                    random.nextInt(livingHeroes.size()));

            System.out.println("\n--- Enemy Turn ---");
            enemy.shootingGun(target);

            if (!party.hasLivingMembers()) {
                System.out.println("Your party has been defeated!");
            }
        }

        if (enemy.isAlive()) {
            System.out.println("Battle lost.");
            return false;
        }

        System.out.println("\nVictory!");
        rewardManager.awardBattleRewards(party, enemy);
        return true;
    }
}

