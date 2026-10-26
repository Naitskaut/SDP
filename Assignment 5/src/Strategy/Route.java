package Strategy;

import java.util.Locale;

//result of a strategy - the same shape for every way of travel
public record Route(String transport, double distanceKm, int minutes, int costTenge) {

    public String summary() {
        return String.format(Locale.ROOT, "%-17s %5.1f km  %4d min  %5d tg", transport, distanceKm, minutes, costTenge);
    }
}
