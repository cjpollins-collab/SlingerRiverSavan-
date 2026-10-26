package Items;

public abstract class Item {
    private String name;
    private String description;
    private int value;

    public Item(String name, String description, int value) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Item name cannot be empty.");
        }

        if (description == null) {
            throw new IllegalArgumentException(
                    "Description cannot be null.");
        }

        if (value < 0) {
            throw new IllegalArgumentException(
                    "Item value cannot be negative.");
        }

        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public abstract String getItemType();

    public void displayInfo() {
        System.out.println(name + " (" + getItemType() + ")");
        System.out.println(description);
        System.out.println("Value: " + value + " gold");
    }

    @Override
    public String toString() {
        return name + " - " + description
                + " (" + value + " gold)";
    }
}
