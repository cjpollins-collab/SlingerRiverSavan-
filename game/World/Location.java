package World;

public class Location {
    private final String name;
    private final String description;
    private final boolean safeZone;
    private boolean discovered;

    public Location(String name, String description,
                    boolean safeZone) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Location name cannot be empty.");
        }

        if (description == null) {
            throw new IllegalArgumentException(
                    "Description cannot be null.");
        }

        this.name = name;
        this.description = description;
        this.safeZone = safeZone;
        this.discovered = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSafeZone() {
        return safeZone;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void discover() {
        discovered = true;
        System.out.println("Discovered: " + name);
    }

    public void displayInfo() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);
        System.out.println("Safe zone: "
                + (safeZone ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        return name + (discovered ? " (Discovered)" : "");
    }
}

