package characters;

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

        if (doesShotMiss()) {
            System.out.println(getName() + " misses their shots!");
            return 0;
        }

        if (ammo <= 0) {
            System.out.println("Not enough ammo!");
            return 0;
        }

        ammo -= 1;
        int damage = target.takeDamage(getAttack() + 5);
        int dealt = target.takeDamage(damage);

        System.out.println(getName() + " unloads rounds!");
        return dealt;
    }
    //
//        /** Restores mana without exceeding the maximum. */
    public void reload(int bullets) {
        if (bullets <= 0) {
            throw new IllegalArgumentException(
                    "You ran out of rounds.");
        }

        ammo = Math.min(mag, ammo + bullets);
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
    public String getStatus() {
        return super.getStatus()
                + " | Rounds: " + ammo + "/" + mag;
    }
}
