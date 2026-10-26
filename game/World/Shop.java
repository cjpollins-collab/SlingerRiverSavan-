package World;

import Items.Inventory;
import Items.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {
    private final String name;
    private final List<Item> stock;

    public Shop(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Shop name cannot be empty.");
        }

        this.name = name;
        stock = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Item> getStock() {
        return Collections.unmodifiableList(stock);
    }

    public void addStock(Item item) {
        if (item != null) {
            stock.add(item);
        }
    }

    public boolean buyItem(int stockIndex, Inventory inventory,
                           int[] playerGold) {
        if (inventory == null || playerGold == null
                || playerGold.length == 0 || playerGold[0] < 0) {
            return false;
        }

        if (stockIndex < 0 || stockIndex >= stock.size()) {
            System.out.println("Invalid shop selection.");
            return false;
        }

        Item item = stock.get(stockIndex);

        if (playerGold[0] < item.getValue()) {
            System.out.println("Not enough gold!");
            return false;
        }

        if (inventory.isFull()) {
            System.out.println("Inventory is full!");
            return false;
        }

        if (!inventory.addItem(item)) {
            return false;
        }

        playerGold[0] -= item.getValue();
        stock.remove(stockIndex);

        System.out.println("Purchased " + item.getName()
                + " for " + item.getValue() + " gold.");

        return true;
    }

    public void displayStock() {
        System.out.println("\n=== " + name + " ===");

        if (stock.isEmpty()) {
            System.out.println("The shop is out of stock.");
            return;
        }

        for (int i = 0; i < stock.size(); i++) {
            Item item = stock.get(i);
            System.out.println((i + 1) + ". " + item.getName()
                    + " - " + item.getValue() + " gold");
        }
    }
}
