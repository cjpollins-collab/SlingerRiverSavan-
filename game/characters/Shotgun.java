package characters;

public class Shotgun extends Slinger{
        private int shells;
        private int shotgun;/*not clips*/
        public Shotgun(String name) {
            super(name, 100, 100,15 , 15);

            shotgun = 5;
            shells = shotgun;
        }
@Override
    public int shootingGun(GameCharacter target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return 0;
        }

    if (shells < 1) {
        System.out.println("Not enough shells!");
        return 0;
    }

    // 2. Consume the ammo
    shells -= 1;

        if (doesAttackMiss(target,0.00)) {
            System.out.println(getName() + " missed the shot!");
            return 0;
        }

        int damage = target.takeDamage(getAttack() + 25);
        int dealt = damage;
        System.out.println(getName() + " pumps "+ target.getName() + " for "
                + damage + " damage!");;
        return dealt;
    }
        public int PumpingShotgun(GameCharacter target) {
            if (target == null || !isAlive() || !target.isAlive()) {
                return 0;
            }
            if (shells < 2) {
                System.out.println("Not enough shells!");
                return 0;
            }

            // 2. Consume the ammo
            shells -= 2;

            if (doesAttackMiss(target,0.00)) {
                System.out.println(getName() + " missed the shot!");
                return 0;
            }

            int damage = target.takeDamage((getAttack() * 2)+5);
            int dealt = damage;
            System.out.println(getName() + " pumps "+ target.getName() + " for "
                    + damage + " damage!");;
            return dealt;
        }
    public void reload() {
        if (!isAlive()) return;
        this.shells = this.shotgun;
        System.out.println(getName() + " reloaded! Ammo is back to " + shotgun + "/" + shotgun);
    }
//    @Override
//    public void reload(int bullets) {
//        if (bullets <= 0) {
//            throw new IllegalArgumentException(
//                    "You ran out of shells.");
//        }
//
//        shells = Math.min(shotgun, shells + bullets);
//    }
        //
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
                this.PumpingShotgun(target);
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
            return 0.10;
     }
        @Override
        public String getCombatRole() {
            return "Shotgun: Close and personal damage";
        }
        //
        @Override
        public String getAmmoStatus() {
            return "Shells: " + shells + "/" + shotgun;
        }

        @Override
        public String getStatus() {
            return super.getStatus()
                    + " | Shells: " + shells + "/" + shotgun;
        }
    }
