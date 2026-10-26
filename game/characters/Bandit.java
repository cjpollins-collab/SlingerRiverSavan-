package characters;

public class Bandit extends Enemy{
    /** Creates a bandit with basic combat statistics. */
    public Bandit() {
        super(name,500,500,35,15,50,10);
    }

    @Override
    public String getCombatRole() {
        return "Bandit: standard enemy";
    }

    @Override
    public String getBehaviorDescription() {
        return "The bandit attacks whoever is in their way.";
    }
}
