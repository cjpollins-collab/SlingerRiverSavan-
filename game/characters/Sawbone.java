package characters;

import Engine.Party;

public class Sawbone extends Slinger{
        private int meds;
        public static final int MAX_MEDS = 100;

        public Sawbone(String name) {
            super(name, 75, 75, 10,35);
            meds = MAX_MEDS;
        }

        public int getMeds() {
            return meds;
        }

        // Restore health to one ally
        public int healAlly(GameCharacter target) {
            int result = 0;
            if (target == null || !target.isAlive() || !isAlive()) {
                System.out.println("Cannot heal that target.");
            } else if (meds < 20) {
                System.out.println(getName() + " ran out of meds!");
            } else {
                meds -= 20;
                int healingAmount = 35;
                int oldHealth = target.getHp();
                target.heal(healingAmount);
                int actualHealing = target.getHp() - oldHealth;
                System.out.println(getName() + " heals "
                        + target.getName() + " for "
                        + actualHealing + " HP!");
                result = actualHealing;
            }

            return result;
        }

        // Restore health to the entire party
 // Make sure to import this at the top of your class file

    // FIX: Changed parameter from GameCharacter to Party
    public int groupHeal(java.util.List<GameCharacter> partyAllies) {
        if (partyAllies == null || !isAlive()) {
            return 0;
        }

        if (meds < 40) {
            System.out.println(getName() + " not enough medical equipment!");
            return 0;
        }

        meds -= 40;
        int totalHealing = 0;

        // Loop through the living members provided by the Party object
        for (GameCharacter member : partyAllies) {
            if (member != null && member.isAlive()) {
                int oldHealth = member.getHp();
                member.heal(20);
                totalHealing += member.getHp() - oldHealth;
            }
        }

        System.out.println(getName() + " healed the party for a total of " + totalHealing + " HP!");
        return totalHealing;
    }



    public void recoverMeds() {
        if (!isAlive()) return;
        this.meds = this.MAX_MEDS;
        System.out.println(getName() + " regained! Medical is back to " + MAX_MEDS + "/" + MAX_MEDS);
    }

    @Override
    public void printCombatMenu() {
        System.out.println("1. Shoot");
        System.out.println("2. Heal");
        System.out.println("3. Group Heal");
        System.out.println("4. Regain meds");
        System.out.println("5. Dodge");

    }

    @Override
    public int getMaxCombatChoices() {
        return 4;
    }

    @Override
    public void executeCombatAction(int choice, GameCharacter target, java.util.List<GameCharacter> allies) {
        switch (choice) {
            case 1:
                this.shootingGun(target);
                break;
            case 2:
                this.healAlly(target);
                break;
            case 3:
                this.groupHeal(allies);
                break;
            case 4:
                this.recoverMeds();
                break;
            case 5:
                this.setDodging(true);
                System.out.println(getName() + " prepares to dodge!");
                break;
            default:
                System.out.println("Invalid action selection.");
                break;
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
        public String getAmmoStatus() {
            return "Medical supplies: " + meds;
        }

        @Override
        public String getStatus() {
            return super.getStatus() + ", Meds: " + meds;
        }
    }
