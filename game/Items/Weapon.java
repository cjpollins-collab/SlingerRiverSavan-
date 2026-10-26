package Items;

public class Weapon extends Item {
    private int attackBonus;

    public Weapon(String name, String description,
                  int value, int attackBonus) {
        super(name, description, value);

        if (attackBonus < 0) {
            throw new IllegalArgumentException(
                    "Attack bonus cannot be negative.");
        }

        this.attackBonus = attackBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public String getItemType() {
        return "Weapon";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Attack Bonus: +" + attackBonus);
    }
}
