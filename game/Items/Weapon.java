package Items;
import Items.WeaponEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Weapon extends Item {
    private int attackBonus;
    private List<WeaponEffect> effects;

    public Weapon(String name, String description,
                  int value, int attackBonus) {
        super(name, description, value);

        if (attackBonus < 0) {
            throw new IllegalArgumentException(
                    "Attack bonus cannot be negative.");
        }

        this.attackBonus = attackBonus;
        this.effects = new ArrayList<>();

    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void addEffect(WeaponEffect effect) {
        if (effect != null && effect != WeaponEffect.NONE
                && !effects.contains(effect)) {
            effects.add(effect);
        }
    }

    public boolean hasEffect(WeaponEffect effect) {
        return effects.contains(effect);
    }

    public List<WeaponEffect> getEffects() {
        return Collections.unmodifiableList(effects);
    }

    @Override
    public String getItemType() {
        return "Weapon";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Attack Bonus: +" + attackBonus);
        System.out.println("Attack Bonus: +" + effects);
    }
}
