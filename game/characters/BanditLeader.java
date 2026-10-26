package characters;

public class BanditLeader extends Enemy{
    private static final String DEFAULT_NAME = "Bandit Leader";
    /** Creates a bandit with basic combat statistics. */
    public BanditLeader() {
        super(DEFAULT_NAME,500,200,30,15,100,10);
    }

    @Override
    public String getCombatRole() {
        return "Bandit Leader: medium enemy";
    }

    @Override
    public String getBehaviorDescription() {
        return "Leader of the Bandits.";
    }
}
