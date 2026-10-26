package Strategy;

import java.util.Objects;

//Context - holds a strategy and delegates to it, doesn't know which one it is
public class Navigator {
    private RouteStrategy strategy;

    public Navigator(RouteStrategy strategy) {
        setStrategy(strategy);
    }

    //strategy can be switched at runtime, the same navigator keeps working
    public void setStrategy(RouteStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Route strategy must not be null");
    }

    public Route planRoute(Trip trip) {
        return strategy.buildRoute(trip);
    }
}
