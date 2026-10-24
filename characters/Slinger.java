import java.util.Scanner;

// Simple character class
class Slinger extends GameCharacter {
    private int level;
    private int experience;
    private int experienceToNextLevel;

    public Slinger(String name, int hp, int maxhp, int attack, int defense) {
        super(name, hp, maxhp, attack, defense);
        level = 1;
        experience = 0;
        experienceToNextLevel = 100;
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

    @Override
    public String getStatus() {
        return super.getStatus()
                + " | Level: " + level
                + " | XP: " + experience + "/" + experienceToNextLevel;
    }
}