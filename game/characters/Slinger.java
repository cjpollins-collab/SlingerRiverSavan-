package characters;

import java.util.Scanner;

// Simple character class
public abstract class Slinger extends GameCharacter {
    private int level;
    private int experience;
    private int experienceToNextLevel;
    public abstract void printCombatMenu();
    public abstract int getMaxCombatChoices();
    public abstract void executeCombatAction(int choice, GameCharacter target, java.util.List<GameCharacter> allies);

    public Slinger(String name, int hp, int maxhp, int attack, int defense) {
        super(name, hp, maxhp, attack, defense);
        level = 1;
        experience = 0;
        experienceToNextLevel = 100;
    }

    /**
     * Overrides the parent miss metric to use the specific Slinger archetype accuracy.
     */
    @Override
    protected double getMissedShot() {
        return getMissChance();
    }
    public void dodge() {
        if (!isAlive()) {
            return;
        }

        setDodging(true);

        System.out.println(getName()
                + " prepares to dodge! Enemy miss chance increased by 15%.");
    }

    /**
     * Adds experience and levels up when enough is earned.
     *
     * @param amount experience to award
     */
    public void gainExperience(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Experience cannot be negative.");
        }

        experience += amount;

        while (experience >= experienceToNextLevel) {
            experience -= experienceToNextLevel;
            levelUp();
        }
    }

    /** Improves the hero's statistics upon leveling up. */
    protected void levelUp() {
        level++;
        experienceToNextLevel += 50;
//        restoreToFullHealth();

        System.out.println(getName() + " reached level " + level
                + "!");
    }

    protected abstract double getMissChance();

    public String getAmmoStatus() {
        return "Ammo: N/A";
    }
    public boolean isDodging() {
        return dodging;
    }

    public void setDodging(boolean dodging) {
        this.dodging = dodging;
    }

    public abstract String getCombatRole();

    @Override
    public String getStatus() {
        return super.getStatus()
                + " | Level: " + level
                + " | XP: " + experience + "/" + experienceToNextLevel;
    }
    public int getLevel() {
        return level;
    }

    /** @return current experience */
    public int getExperience() {
        return experience;
    }

    /** @return experience needed for the next level */
    public int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }
}