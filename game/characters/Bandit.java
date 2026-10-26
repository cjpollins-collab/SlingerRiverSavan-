package characters;

public class Bandit extends Enemy{
    private static final String DEFAULT_NAME = "Bandit";
    /** Creates a bandit with basic combat statistics. */
    public Bandit() {
        super(DEFAULT_NAME,100,100,35,15,50,10);
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
