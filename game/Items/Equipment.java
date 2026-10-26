package Items;

public class Equipment {
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public Equipment() {
        equippedWeapon = null;
        equippedArmor = null;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public boolean equipWeapon(Weapon weapon) {
        if (weapon == null) {
            System.out.println("Cannot equip a null weapon.");
            return false;
        }

        equippedWeapon = weapon;

        System.out.println("Equipped weapon: "
                + weapon.getName());

        return true;
    }

    public boolean equipArmor(Armor armor) {
        if (armor == null) {
            System.out.println("Cannot equip null armor.");
            return false;
        }

        equippedArmor = armor;

        System.out.println("Equipped armor: "
                + armor.getName());

        return true;
    }

    public Weapon unequipWeapon() {
        Weapon previousWeapon = equippedWeapon;
        equippedWeapon = null;

        if (previousWeapon != null) {
            System.out.println("Unequipped weapon: "
                    + previousWeapon.getName());
        }

        return previousWeapon;
    }

    public Armor unequipArmor() {
        Armor previousArmor = equippedArmor;
        equippedArmor = null;

        if (previousArmor != null) {
            System.out.println("Unequipped armor: "
                    + previousArmor.getName());
        }

        return previousArmor;
    }

    public int getTotalAttackBonus() {
        if (equippedWeapon == null) {
            return 0;
        }

        return equippedWeapon.getAttackBonus();
    }

    public int getTotalDefenseBonus() {
        if (equippedArmor == null) {
            return 0;
        }

        return equippedArmor.getDefenseBonus();
    }

    public void displayEquipment() {
        System.out.println("\n=== EQUIPMENT ===");

        System.out.println("Weapon: "
                + (equippedWeapon == null
                ? "None" : equippedWeapon.getName()));

        System.out.println("Armor: "
                + (equippedArmor == null
                ? "None" : equippedArmor.getName()));

        System.out.println("Attack Bonus: +"
                + getTotalAttackBonus());

        System.out.println("Defense Bonus: +"
                + getTotalDefenseBonus());
    }
}

