package Items;

import characters.GameCharacter;
import characters.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private final List<Item> items;
    private final int capacity;

    public Inventory() {
        this(20);
    }

    public Inventory(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Inventory capacity must be positive.");
        }

        this.capacity = capacity;
        items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (item == null) {
            System.out.println("Cannot add a null item.");
            return false;
        }

        if (items.size() >= capacity) {
            System.out.println("Inventory is full!");
            return false;
        }

        items.add(item);
        System.out.println(item.getName()
                + " added to inventory.");
        return true;
    }

    public boolean removeItem(Item item) {
        if (item == null) {
            return false;
        }

        boolean removed = items.remove(item);

        if (removed) {
            System.out.println(item.getName()
                    + " removed from inventory.");
        }

        return removed;
    }

    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }

        return items.get(index);
    }

    public boolean usePotion(int index, GameCharacter target) {
        if (target == null) {
            System.out.println("Invalid potion target.");
            return false;
        }

        if (index < 0 || index >= items.size()) {
            System.out.println("Invalid inventory selection.");
            return false;
        }

        Item item = items.get(index);

        if (!(item instanceof Potion)) {
            System.out.println("That item is not a potion.");
            return false;
        }

        Potion potion = (Potion) item;

        if (potion.applyTo(target)) {
            items.remove(index);
            System.out.println(potion.getName()
                    + " was used.");
            return true;
        }

        return false;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int getSize() {
        return items.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public void displayInventory() {
        System.out.println("\n=== INVENTORY ===");
        System.out.println("Items: " + items.size()
                + "/" + capacity);

        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". "
                    + items.get(i));
        }
    }
}

