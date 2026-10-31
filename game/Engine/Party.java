package Engine;


import Items.Inventory;
import characters.GameCharacter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Party {
    private final List<GameCharacter> members;
    private final Inventory inventory;
    private int gold;

    public Party() {
        members = new ArrayList<>();
        inventory = new Inventory();
        gold = 100;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        if (gold >= 0) {
            this.gold = gold;
        }
    }

    public boolean hasMember(String name) {
        for (GameCharacter member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addMember(GameCharacter character) {
        if (character == null || members.contains(character)) {
            return false;
        }

        members.add(character);
        System.out.println(character.getName()
                + " joined the party.");
        return true;
    }

    public List<GameCharacter> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public void addGold(int amount) {
        if (amount > 0) {
            gold += amount;
        }
    }

    public boolean spendGold(int amount) {
        if (amount < 0 || amount > gold) {
            System.out.println("Not enough gold!");
            return false;
        }

        gold -= amount;
        return true;
    }

    public boolean hasLivingMembers() {
        for (GameCharacter member : members) {
            if (member.isAlive()) {
                return true;
            }
        }

        return false;
    }

    public List<GameCharacter> getLivingMembers() {
        List<GameCharacter> living = new ArrayList<>();

        for (GameCharacter member : members) {
            if (member.isAlive()) {
                living.add(member);
            }
        }

        return living;
    }

    public void displayParty() {
        System.out.println("\n=== YOUR PARTY ===");

        if (members.isEmpty()) {
            System.out.println("Your party is empty.");
        }

        for (GameCharacter member : members) {
            System.out.println(member.getStatus());
        }

        System.out.println("Gold: " + gold);
    }
}
