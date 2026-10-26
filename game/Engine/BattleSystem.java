package Engine;

import characters.*;

import java.util.List;
import java.util.Random;

public class BattleSystem {
    private final RewardManager rewardManager;
    private final Random random;

    public BattleSystem() {
        rewardManager = new RewardManager();
        random = new Random();
    }

    public boolean startBattle(Party party, Enemy enemy, CombatActionProvider actions) {
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
                if (!slinger.isAlive()) {
                    continue;
                }

                if (!enemy.isAlive()) {
                    break;
                }

                int action = actions.chooseAction(slinger, enemy);

                if (action == 1) {
                    slinger.shootingGun(enemy);

                } else if (action == 2 && slinger instanceof Gunner) {
                    ((Gunner) slinger).rapidFire(enemy);
                } else if (action == 2 && slinger instanceof Shotgun) {
                    ((Shotgun) slinger).PumpingShotgun(enemy);
                } else if (action == 2 && slinger instanceof TriggerHappy) {
                    ((TriggerHappy) slinger).sprayAndPray(enemy);
                } else if (action == 2 && slinger instanceof Sawbone) {
                    GameCharacter ally = actions.chooseAlly(party);

                    if (ally != null) {
                        ((Sawbone) slinger).healAlly(ally);
                    }
                } else if (action == 3 && slinger instanceof Sawbone) {
                    ((Sawbone) slinger).groupHeal(party.getLivingMembers());

                }
                if (action == 3 && slinger instanceof Gunner) {
                    ((Gunner) slinger).reload();
                } else if (action == 3 && slinger instanceof Shotgun) {
                    ((Shotgun) slinger).reload();
                } else if (action == 3 && slinger instanceof TriggerHappy) {
                    ((TriggerHappy) slinger).reload();
                }

                    else if (action == 4 && slinger instanceof Sawbone) {
                        ((Sawbone) slinger).recoverMeds();
                }
                    else {
                    System.out.println("Invalid action. You lose this turn.");
                }

                if (!enemy.isAlive()) {
                    break;
                }

                if (party.hasLivingMembers()) {
                    List<GameCharacter> livingMembers = party.getLivingMembers();
                    GameCharacter target = livingMembers.get(random.nextInt(livingMembers.size()));

                    System.out.println("\n" + enemy.getName() + " attacks!");
                    enemy.shootingGun(target);

                    // Check if the target was just knocked down
                    if (!target.isAlive()) {
                        System.out.println(target.getName() + " has been knocked down!");
                    }
                }

                // 4. CHECK DEFEAT
                if (!party.hasLivingMembers()) {
                    System.out.println("\nYour party has been defeated!");
                    return false;
                }
            }


        }
            System.out.println("\nVictory! You defeated " + enemy.getName() + ".");
            rewardManager.awardBattleRewards(party, enemy);
            return true;
        }
        // Keep compatibility with code that starts battles without a console.
        public boolean startBattle (Party party, Enemy enemy){
            System.out.println(
                    "A combat action provider is required for this battle."
            );
            return false;
        }
    }

