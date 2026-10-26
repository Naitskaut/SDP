package Strategy;

//Strategy - one method that every way of travel implements in its own way
public interface RouteStrategy {
    Route buildRoute(Trip trip);
}
