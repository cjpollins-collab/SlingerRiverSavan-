// Simple character class
public abstract class GameCharacter {
    String name;
    int hp;
    int maxhp;
    int attack;
    int defense;
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
        return attack;
    }
    /** @return the character's defense */
    public int getDefense() {
        return defense;
    }

    public int takeIncomingDamage(int incomingDamage) {
        if (incomingDamage < 0) {
            throw new IllegalArgumentException(
                    "Damage cannot be negative.");
        }

        if (!isAlive() || incomingDamage == 0) {
            return 0;
        }

        int actualDamage = Math.max(1,
                incomingDamage - defense);

        hp = Math.max(0, hp - actualDamage);
        return actualDamage;
    }
    boolean isAlive() {
        return hp > 0;
    }
    int takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
        return dmg;
    }
    public String getStatus() {
        return name + " HP: " + hp + "/" + maxhp
                + " | ATK: " + attack
                + " | DEF: " + defense;
    }
}