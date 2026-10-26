package Engine;

import characters.Enemy;
import characters.GameCharacter;
import characters.Slinger;

public class RewardManager {

    public void awardBattleRewards(Party party, Enemy enemy) {
        if (party == null || enemy == null) {
            return;
        }

        int experience = enemy.getExperienceReward();
        int gold = enemy.getGoldReward();

        for (GameCharacter member : party.getMembers()) {
            if (member instanceof Slinger) {
                ((Slinger) member).gainExperience(experience);
            }
        }

        party.addGold(gold);

        System.out.println("\n=== BATTLE REWARDS ===");
        System.out.println("Party earned " + experience
                + " XP per hero.");
        System.out.println("Party earned " + gold + " gold.");
        System.out.println("Total gold: " + party.getGold());
    }
}

