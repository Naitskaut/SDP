package Strategy;

//roads are longer than a straight line, plus time to park and money for fuel
public class CarStrategy implements RouteStrategy {
    private static final double ROAD_DETOUR = 1.3;
    private static final double SPEED_KMH = 40;
    private static final int PARKING_MINUTES = 7;
    private static final double LITERS_PER_KM = 0.08;
    private static final int FUEL_PRICE_TENGE = 250;

    @Override
    public Route buildRoute(Trip trip) {
        double distance = trip.distanceKm() * ROAD_DETOUR;
        int minutes = (int) Math.round(distance / SPEED_KMH * 60) + PARKING_MINUTES;
        int cost = (int) Math.round(distance * LITERS_PER_KM * FUEL_PRICE_TENGE);
        return new Route("Car", distance, minutes, cost);
    }
}
