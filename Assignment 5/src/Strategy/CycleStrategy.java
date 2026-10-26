package Strategy;

//bike lanes add a little to the distance, but a bike is 3 times faster than walking
public class CycleStrategy implements RouteStrategy {
    private static final double BIKE_LANE_DETOUR = 1.1;
    private static final double SPEED_KMH = 15;

    @Override
    public Route buildRoute(Trip trip) {
        double distance = trip.distanceKm() * BIKE_LANE_DETOUR;
        int minutes = (int) Math.round(distance / SPEED_KMH * 60);
        return new Route("Bicycle", distance, minutes, 0);
    }
}
