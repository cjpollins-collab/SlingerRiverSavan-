package characters;

/**
 * Abstract superclass for enemies encountered during the campaign.
 */
public abstract class Enemy extends GameCharacter {
    private int experienceReward;
    private int goldReward;

    /**
     * Creates an enemy with combat statistics and rewards.
     *
     * @param name enemy name
     * @param hp maximum health
     * @param attack attack power
     * @param defense defense value
     * @param experienceReward XP awarded for victory
     * @param goldReward gold awarded for victory
     */
    public Enemy(String name, int hp, int maxhp, int attack, int defense,
                 int experienceReward, int goldReward) {
        super(name, hp, maxhp, attack, defense);

        if (experienceReward < 0 || goldReward < 0) {
            throw new IllegalArgumentException(
                    "Enemy rewards cannot be negative.");
        }

        this.experienceReward = experienceReward;
        this.goldReward = goldReward;
    }
        /** @return experience awarded for defeating this enemy */
    public int getExperienceReward() {
        return experienceReward;
    }

    /** @return gold awarded for defeating this enemy */
    public int getGoldReward() {
        return goldReward;
    }

    public abstract String getCombatRole();

    /**
     * Describes the enemy's preferred battle behavior.
     *
     * @return enemy behavior description
     */
    public abstract String getBehaviorDescription();


        public int attackTarget(GameCharacter target) {
            if (target == null || !isAlive() || !target.isAlive()) {
                return 0;
            }


            if (target.isDodging()) {
                target.setDodging(false);

                // Dodge succeeds 15% of the time.
                if (Math.random() < 0.15) {
                    System.out.println(target.getName() + " dodged the attack!");
                    return 0;
                } else {
                    System.out.println(target.getName() + " failed to dodge");
                }
            }

            int damage = target.takeDamage(getAttack());

            System.out.println(getName() + " attacks "
                    + target.getName() + " for " + damage + " damage!");

            return damage;
        }
}
