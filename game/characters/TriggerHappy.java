package characters;

import Items.WeaponEffect;

public class TriggerHappy extends Slinger{
    private int ammo;
    private int mag;/*not clips*/
    public TriggerHappy(String name) {
        super(name, 150, 150, 5, 40);

        mag = 30;
        ammo = mag;
    }
    @Override
    public int shootingGun(GameCharacter target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }

        if (ammo < 1) {
            System.out.println("Not enough shells!");
            return 0;
        }

        // 2. Consume the ammo
        ammo -= 1;

        if (doesAttackMiss(target,-0.10)) {
            System.out.println(getName() + " misses their shot!");
            return 0;
        }

        int damage = target.takeDamage(getAttack())*2;

        applyWeaponEffects(target);

        System.out.println(getName() + " unloads rounds on " + target.getName() + " for "
                + damage + " damage!");;
        return damage;
    }

    public int sprayAndPray(GameCharacter target) {
        // 1. Initial safety checks
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }

        if (ammo < 1) {
            System.out.println("Not enough ammo!");
            return 0;
        }

        int totalDamageDealt = 0;
        int shotsFired = 0; // Initialize a counter to track the rounds fired
        System.out.println(getName() + " starts spraying a 5-round burst at " + target.getName() + "!");

        // 2. Loop until out of ammo, target dies, OR 5 rounds have been fired
        while (ammo > 0 && target.isAlive() && shotsFired < 5) {

            ammo -= 1;        // Consume one ammo
            shotsFired += 1;  // Increment our shot counter

            // Check if this specific shot misses
            if (doesAttackMiss(target,0.00)) {
                System.out.println("  Shot #" + shotsFired + " misses!");
                continue; // Move to the next bullet
            }

            // Calculate and apply damage for a successful hit
            int baseDamage = calculateAbilityDamage((getAttack() + 5) * 2);
            int damageDealtThisShot =
                    dealWeaponDamageWithoutCritical(target, baseDamage);

            if (!target.isAlive()
                    && hasWeaponEffect(WeaponEffect.RESTORE_AMMO_ON_KILL)) {
                ammo = Math.min(mag, ammo + 5);
                System.out.println(getName() + " restored 5 ammo!");
            }

            totalDamageDealt += damageDealtThisShot;

            System.out.println("  Shot #" + shotsFired + " hits! Dealt " + damageDealtThisShot + " damage.");
        }

        // 3. Final summary statement
        System.out.println(getName() + " finished unloading! Shots fired: " + shotsFired + ". Total damage: " + totalDamageDealt);
        return totalDamageDealt;
    }
    public void reload() {
        if (!isAlive()) return;
        this.ammo = this.mag;
        System.out.println(getName() + " reloaded! Ammo is back to " + mag + "/" + mag);
    }

    //

@Override
public void printCombatMenu() {
    System.out.println("1. Shoot");
    System.out.println("2. Spray and Pray");
    System.out.println("3. Reload");
    System.out.println("4. Dodge");
}

    @Override
    public int getMaxCombatChoices() {
        return 3;
    }
public void executeCombatAction(int choice, GameCharacter target, java.util.List<GameCharacter> allies) {
    switch (choice) {
        case 1:
            this.shootingGun(target);
            break;
        case 2:
            this.sprayAndPray(target);
            break;
        case 3:
            this.reload();
            break;
        case 5:
            this.setDodging(true);
            System.out.println(getName() + " prepares to dodge!");
            break;
        default:
            System.out.println("Invalid action selection.");
            break;
    }
}
    @Override
    protected double getMissChance() {
        return 0.35;
    }
    @Override
    public String getCombatRole() {
        return "Trigger Happy: Happy to be shooting";
    }
    //
    @Override
    public String getAmmoStatus() {
        return "Rounds: " + ammo + "/" + mag;
    }

    @Override
    public String getStatus() {
        return super.getStatus()
                + " | Rounds: " + ammo + "/" + mag;
    }
}
