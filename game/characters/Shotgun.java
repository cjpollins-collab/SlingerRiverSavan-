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

            if (doesShotMiss()) {
                System.out.println(getName() + " missed the shot!");
                return 0;
            }

            if (shells <= 0) {
                System.out.println("Not enough shells!");
                return 0;
            }

            shells -= 1;
            int damage = target.takeDamage(getAttack() * 2);
            int dealt = damage;

            System.out.println(getName() + " pumps out a shell!");
            return dealt;
        }
        //
//        /** Restores mana without exceeding the maximum. */
        public void reload(int bullets) {
            if (bullets <= 0) {
                throw new IllegalArgumentException(
                        "You ran out off ammo.");
            }

            shells = Math.min(shotgun, shells + bullets);
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
        public String getStatus() {
            return super.getStatus()
                    + " | Shells: " + shells + "/" + shotgun;
        }
    }
