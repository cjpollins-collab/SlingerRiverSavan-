package Items;

public class Armor extends Item {
    private int defenseBonus;

    public Armor(String name, String description,
                 int value, int defenseBonus) {
        super(name, description, value);

        if (defenseBonus < 0) {
            throw new IllegalArgumentException(
                    "Defense bonus cannot be negative.");
        }

        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public String getItemType() {
        return "Armor";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Defense Bonus: +" + defenseBonus);
    }
}

