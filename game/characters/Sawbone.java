package characters;

public class Sawbone extends Slinger{
        private int meds;
        private static final int MAX_MEDS = 100;

        public Sawbone(String name) {
            super(name, 75, 75, 5,35);
            meds = MAX_MEDS;
        }

        public int getMana() {
            return meds;
        }

        // Restore health to one ally
        public int healAlly(GameCharacter target) {
            if (target == null || !target.isAlive() || !isAlive()) {
                System.out.println("Cannot heal that target.");
                return 0;
            }

            if (meds < 20) {
                System.out.println(getName() + " ran out of meds!");
                return 0;
            }

            meds -= 20;

            int healingAmount = 35;
            int oldHealth = target.getHp();

            target.heal(healingAmount);

            int actualHealing = target.getHp() - oldHealth;

            System.out.println(getName() + " heals "
                    + target.getName() + " for "
                    + actualHealing + " HP!");

            return actualHealing;
        }

        // Restore health to the entire party
        public int groupHeal(GameCharacter[] party) {
            if (party == null || !isAlive()) {
                return 0;
            }

            if (meds < 40) {
                System.out.println(getName() + " not enough medical equipment!");
                return 0;
            }

            meds -= 40;
            int totalHealing = 0;

            for (GameCharacter member : party) {
                if (member != null && member.isAlive()) {
                    int oldHealth = member.getHp();

                    member.heal(20);

                    totalHealing += member.getHp() - oldHealth;
                }
            }

            System.out.println(getName()
                    + " uses Group Heal! The party recovers "
                    + totalHealing + " HP in total.");

            return totalHealing;
        }

        public void recoverMeds(int amount) {
            if (amount > 0) {
                meds = Math.min(MAX_MEDS, meds + amount);
            }
        }

        @Override
        protected double getMissChance() {
            return 0.15; // 15% chance to miss regular attacks
        }

        @Override
        public String getCombatRole() {
            return "Healer";
        }

        @Override
        public String getStatus() {
            return super.getStatus() + ", Meds: " + meds;
        }
    }
