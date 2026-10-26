package Strategy;

import java.util.Objects;

//where the user wants to go - the same input for every strategy
public record Trip(String from, String to, double distanceKm) {

    public Trip {
        Objects.requireNonNull(from, "Start point must not be null");
        Objects.requireNonNull(to, "Destination must not be null");
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be positive, got " + distanceKm);
        }
    }
}
