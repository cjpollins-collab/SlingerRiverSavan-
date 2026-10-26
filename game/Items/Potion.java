package Items;

import characters.GameCharacter;
import characters.Sawbone;

public class Potion extends Item {
    public enum PotionType {
        HEALTH,
        MEDICAL
    }

    private PotionType potionType;
    private int restoreAmount;

    public Potion(String name, String description,
                  int value, PotionType potionType,
                  int restoreAmount) {
        super(name, description, value);

        if (potionType == null) {
            throw new IllegalArgumentException(
                    "Potion type cannot be null.");
        }

        if (restoreAmount <= 0) {
            throw new IllegalArgumentException(
                    "Restore amount must be positive.");
        }

        this.potionType = potionType;
        this.restoreAmount = restoreAmount;
    }

    public PotionType getPotionType() {
        return potionType;
    }

    public int getRestoreAmount() {
        return restoreAmount;
    }

    public boolean applyTo(GameCharacter target) {
        if (target == null || !target.isAlive()) {
            System.out.println("Invalid potion target.");
            return false;
        }

        if (potionType == PotionType.HEALTH) {
            int oldHealth = target.getHp();

            target.heal(restoreAmount);

            int restored = target.getHp() - oldHealth;

            if (restored == 0) {
                System.out.println(target.getName()
                        + " does not need healing.");
                return false;
            }

            System.out.println(target.getName() + " recovered "
                    + restored + " HP!");

            return true;
        }

        if (target instanceof Sawbone) {
            ((Sawbone) target).recoverMeds(restoreAmount);
            System.out.println(target.getName()
                    + " recovered meds!");
            return true;
        }

        System.out.println("This character cannot use meds.");
        return false;
    }
    @Override
    public String getItemType() {
        return potionType + " Potion";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Restores: " + restoreAmount
                + (potionType == PotionType.HEALTH
                ? " HP" : " meds"));
    }
}
