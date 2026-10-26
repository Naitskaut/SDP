package Strategy;

//a pedestrian goes almost in a straight line through yards and parks, for free
public class WalkStrategy implements RouteStrategy {
    private static final double SPEED_KMH = 5;

    @Override
    public Route buildRoute(Trip trip) {
        double distance = trip.distanceKm();
        int minutes = (int) Math.round(distance / SPEED_KMH * 60);
        return new Route("Walk", distance, minutes, 0);
    }
}
