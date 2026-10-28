package World;
import World.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map {
    private final List<Location> locations;
    private Location currentLocation;

    public Map() {
        locations = new ArrayList<>();
        currentLocation = null;
    }

    public void addLocation(Location location) {
        if (location == null) {
            return;
        }

        for (Location existing : locations) {
            if (existing.getName().equalsIgnoreCase(
                    location.getName())) {
                System.out.println(
                        "A location with that name already exists.");
                return;
            }
        }

        locations.add(location);

        if (currentLocation == null) {
            currentLocation = location;
            location.discover();
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }

    public boolean travelTo(String locationName) {
        if (locationName == null) {
            return false;
        }

        for (Location location : locations) {
            if (location.getName().equalsIgnoreCase(locationName)) {
                currentLocation = location;
                location.discover();

                System.out.println("You traveled to "
                        + location.getName() + ".");

                return true;
            }
        }

        System.out.println("Location not found: " + locationName);
        return false;
    }

    public void displayMap() {
        System.out.println("\n=== WORLD MAP ===");

        if (locations.isEmpty()) {
            System.out.println("No locations discovered.");
            return;
        }

        for (Location location : locations) {
            String marker = location == currentLocation
                    ? " [Current]" : "";

            System.out.println("- " + location + marker);
        }
    }
}

