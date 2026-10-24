public class Gunner extends Slinger {
    private int ammo;
    private int mag;/*not clips*/
    public Gunner(String name, int hp, int maxhp, int attack, int defense) {
        super(name, 150, 150, 150, 75);

        mag = 12;
        ammo = mag;
    }
        public int shootingGun(GameCharacter target) {
            if (super.name == null || !isAlive() || !target.isAlive()) {
                return 0;
            }

            if (ammo <= 0) {
                System.out.println("Not enough ammo!");
                return 0;
            }

            ammo -= 1;
            int damage = getAttack() + 75;
            int dealt = target.takeDamage(damage);

            System.out.println(getName() + " casts Fireball!");
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