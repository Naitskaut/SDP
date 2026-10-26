package Strategy;

//added last - Navigator and the other strategies were not changed for it
public class PublicTransportStrategy implements RouteStrategy {
    private static final double BUS_ROUTE_DETOUR = 1.2;
    private static final double SPEED_KMH = 20;
    private static final int WAITING_MINUTES = 10;
    private static final int WALK_TO_STOPS_MINUTES = 6;
    private static final int FARE_TENGE = 110;

    @Override
    public Route buildRoute(Trip trip) {
        double distance = trip.distanceKm() * BUS_ROUTE_DETOUR;
        int rideMinutes = (int) Math.round(distance / SPEED_KMH * 60);
        int minutes = WALK_TO_STOPS_MINUTES + WAITING_MINUTES + rideMinutes;
        return new Route("Public transport", distance, minutes, FARE_TENGE);
    }
}
