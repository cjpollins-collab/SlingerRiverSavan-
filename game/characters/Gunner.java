package characters;

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

            if (doesShotMiss()) {
                System.out.println(getName() + " missed the shot!");
                return 0;
            }


            if (ammo <= 0) {
                System.out.println("Not enough ammo!");
                return 0;
            }

            ammo -= 1;
            int damage = target.takeDamage(getAttack() + 30);
            int dealt = damage;

            System.out.println(getName() + " shoots!");
            return dealt;
        }
//
//        /** Restores mana without exceeding the maximum. */
        public void reload(int bullets) {
            if (bullets <= 0) {
                throw new IllegalArgumentException(
                        "You ran out off ammo.");
            }

            ammo = Math.min(mag, ammo + bullets);
        }

    protected double getMissChance() {
        return 0.10;
    }
        @Override
        public String getCombatRole() {
            return "Gunner: shoot out a good amount of damage";
        }
//
        @Override
        public String getStatus() {
            return super.getStatus()
                    + " | Ammo: " + ammo + "/" + mag;
        }
    }