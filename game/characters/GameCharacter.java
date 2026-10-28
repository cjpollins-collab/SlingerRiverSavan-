package characters;

import java.util.Random;
import Items.Equipment;

// Simple character class
public abstract class GameCharacter {
    String name;
    int hp;
    int maxhp;
    int attack;
    int defense;
    boolean dodging;
    private Equipment equipment = new Equipment();
    GameCharacter(String name, int hp, int maxhp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxhp = maxhp;
        this.attack = attack;
        this.defense = defense;
    }
    /** @return the character's name */
    public String getName() {
        return name;
    }
    /** @return the character's current health */
    public int getHp() {
        return hp;
    }
    /** @return the character's maximum health */
    public int getMaxhp() {
        return maxhp;
    }
    /** @return the character's base attack power */
    public int getAttack() {
        return attack + equipment.getTotalAttackBonus();
    }
    public int getDefense() {
        return defense + equipment.getTotalDefenseBonus();
    }
    /** @return the character's defense */
    public int shootingGun(GameCharacter target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }

        if (doesShotMiss()) {
            System.out.println(getName() + " missed the shot!");
            return 0;
        }


        int damage = target.takeDamage(getAttack());

        System.out.println(getName() + " attacks "
                + target.getName() + " for "
                + damage + " damage!");

        return damage;
    }

//    public int dodge(int move) LOOOOOOOOOOOOOOOOOOOOOK IN to THISSSSSSSSSSSSSSSSSSSSSS!!!!!!!!!!!!!!

    public static  final Random RANDOM = new Random();

    protected double getMissedShot() {
        return getMissChance();
    }

    protected double getMissChance() {
        return 0.20;
    }

    protected boolean doesShotMiss() {
        return RANDOM.nextDouble() < getMissedShot();
    }


    protected boolean doesAttackMiss(GameCharacter target, double abilityMissChance) {
        if (target == null || !target.isAlive()) {
            return true;
        }

        boolean wasDodging = target.isDodging();

        double missChance = getMissChance() + abilityMissChance;

        if (wasDodging) {
            missChance += 0.15;
            target.setDodging(false);
        }
        missChance = Math.max(0.0, Math.min(1.0, missChance));

        return RANDOM.nextDouble() < missChance;
    }

    public boolean isAlive() {
        return hp > 0;
    }
    public int takeDamage(int incomingDamage) {
        if (incomingDamage < 0 || !isAlive()) {
            return 0;
        }
        // Dodge avoids the next incoming attack.
        if (getDefense() > incomingDamage) {
            System.out.println(getName() + "'s defense blocked the attack!");
            return 0;
        }

        int actualDamage = Math.max(0, incomingDamage - getDefense());
        hp = Math.max(0, hp - actualDamage);

        return actualDamage;
    }

    public boolean isDodging() {
        return dodging;
    }

    public void setDodging(boolean dodging) {
        this.dodging = dodging;
    }
    public String getStatus() {
        return name + " HP: " + hp + "/" + maxhp
                + " | ATK: " + attack
                + " | DEF: " + defense;
    }



    public int heal(int healingAmount) {
        if (healingAmount > 0) {
            hp = Math.min(maxhp, hp + healingAmount);
        }
        if (healingAmount < 0) {
            throw new IllegalArgumentException(
                    "Healing cannot be negative.");
        }
        if (!isAlive()) {
            return 0;
        }

        int oldHealth = hp;
        return hp - oldHealth;
    }

    public Equipment getEquipment() {
        return equipment;
    }
    }