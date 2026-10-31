package characters;
import Console.ConsoleUI;
import Items.WeaponEffect;

public class Gunner extends Slinger {
    private int ammo;
    private int mag;/*not clips*/
    public Gunner(String name) {
        super(name, 150, 150, 10, 30);

        mag = 12;
        ammo = mag;
    }



        @Override
        public int shootingGun(GameCharacter target) {
            if (target == null || !isAlive() || !target.isAlive()) {
                return 0;
            }
            if (ammo < 1) {
                System.out.println("Out of ammo! Needs to reload");
                return 0;
            }

            // 2. Consume the ammo
            ammo -= 1;

            if (doesAttackMiss(target,0.00)) {
                System.out.println(getName() + " missed the shot!");
                return 0;
            }

            int damage = getAttack() + 20;
            int damageDealt = dealWeaponDamage(target, damage);

            applyWeaponEffects(target);

            if (!target.isAlive()
                    && hasWeaponEffect(WeaponEffect.RESTORE_AMMO_ON_KILL)) {
                ammo = Math.min(mag, ammo + 2);
                System.out.println(getName() + " restored 2 ammo!");
            }
            System.out.println(getName() + " shoots " + target.getName() + " for "
                    + damageDealt + " damage!");;
            return damageDealt;
        }

    public int rapidFire(GameCharacter target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }

        if (ammo < 2) {
            System.out.println("Not enough ammo for Rapid Fire!");
            return 0;
        }

        ammo -= 2;
        if (doesAttackMiss(target,0.00)) {
            System.out.println(getName() + " missed their shots!");
            return 0;
        }



        int damage = calculateAbilityDamage(getAttack() + 45);
        int damageDealt = dealWeaponDamageWithoutCritical(target, damage);

        applyWeaponEffects(target);

        if (!target.isAlive()
                && hasWeaponEffect(WeaponEffect.RESTORE_AMMO_ON_KILL)) {
            ammo = Math.min(mag, ammo + 2);
            System.out.println(getName() + " restored 2 ammo!");
        }

        System.out.println(getName() + " two shots " + target.getName() + " for "
                + damageDealt + " damage!");;
        return damageDealt;
    }
//
        /** Restores ammo without exceeding the maximum. */
        public void reload() {
            if (!isAlive()) return;
            this.ammo = this.mag;
            System.out.println(getName() + " reloaded! Ammo is back to " + mag + "/" + mag);
        }

    protected double getMissChance() {

        return 0.10;
    }

    @Override
    public void printCombatMenu() {
        System.out.println("1. Shoot");
        System.out.println("2. Rapid Fire");
        System.out.println("3. Reload");
        System.out.println("4. Dodge");
    }

    @Override
    public int getMaxCombatChoices() {
        return 3;
    }

    @Override
    public void executeCombatAction(int choice, GameCharacter target, java.util.List<GameCharacter> allies) {
        switch (choice) {
            case 1:
                this.shootingGun(target);
                break;
            case 2:
                this.rapidFire(target);
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
        public String getCombatRole() {

            return "Gunner: shoot out a good amount of damage";
        }

        @Override
        public String getAmmoStatus() {
            return "Ammo: " + ammo + "/" + mag;
        }

        @Override
        public String getStatus() {
            return super.getStatus()
                    + " | Ammo: " + ammo + "/" + mag;
        }
    }