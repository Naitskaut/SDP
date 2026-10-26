import Strategy.CarStrategy;
import Strategy.CycleStrategy;
import Strategy.Navigator;
import Strategy.PublicTransportStrategy;
import Strategy.Route;
import Strategy.Trip;
import Strategy.WalkStrategy;

//tests without a framework - every test checks one concept, run with main()
public class NavigatorTest {
    private static final Trip TEN_KM_TRIP = new Trip("A", "B", 10);
    private static int passed;
    private static int failed;

    public static void main(String[] args) {
        run("car adds road detour, parking and fuel", NavigatorTest::carAddsRoadDetourParkingAndFuel);
        run("walking is straight and free", NavigatorTest::walkingIsStraightAndFree);
        run("cycling is faster than walking", NavigatorTest::cyclingIsFasterThanWalking);
        run("public transport adds waiting and fare", NavigatorTest::publicTransportAddsWaitingAndFare);
        run("navigator uses the strategy it was switched to", NavigatorTest::navigatorUsesSwitchedStrategy);
        run("navigator works with any new strategy", NavigatorTest::navigatorWorksWithAnyNewStrategy);
        run("navigator rejects null strategy", NavigatorTest::navigatorRejectsNullStrategy);
        run("trip rejects non-positive distance", NavigatorTest::tripRejectsNonPositiveDistance);

        System.out.println("\n" + passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void carAddsRoadDetourParkingAndFuel() {
        Route route = new CarStrategy().buildRoute(TEN_KM_TRIP);
        assertRoute(route, 13.0, 27, 260);
    }

    private static void walkingIsStraightAndFree() {
        Route route = new WalkStrategy().buildRoute(TEN_KM_TRIP);
        assertRoute(route, 10.0, 120, 0);
    }

    private static void cyclingIsFasterThanWalking() {
        Route cycle = new CycleStrategy().buildRoute(TEN_KM_TRIP);
        Route walk = new WalkStrategy().buildRoute(TEN_KM_TRIP);
        check(cycle.minutes() < walk.minutes(), "cycle " + cycle.minutes() + " min, walk " + walk.minutes() + " min");
    }

    private static void publicTransportAddsWaitingAndFare() {
        Route route = new PublicTransportStrategy().buildRoute(TEN_KM_TRIP);
        assertRoute(route, 12.0, 52, 110);
    }

    private static void navigatorUsesSwitchedStrategy() {
        Navigator navigator = new Navigator(new CarStrategy());

        navigator.setStrategy(new WalkStrategy());
        Route route = navigator.planRoute(TEN_KM_TRIP);

        check(route.transport().equals("Walk"), "expected Walk, got " + route.transport());
    }

    //a strategy written right here - Navigator accepts it without any change
    private static void navigatorWorksWithAnyNewStrategy() {
        Navigator navigator = new Navigator(trip -> new Route("Teleport", trip.distanceKm(), 0, 999));

        Route route = navigator.planRoute(TEN_KM_TRIP);

        assertRoute(route, 10.0, 0, 999);
    }

    private static void navigatorRejectsNullStrategy() {
        Navigator navigator = new Navigator(new CarStrategy());
        assertThrows(NullPointerException.class, () -> navigator.setStrategy(null));
    }

    private static void tripRejectsNonPositiveDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Trip("A", "B", 0));
    }

    private static void assertRoute(Route route, double distanceKm, int minutes, int costTenge) {
        check(Math.abs(route.distanceKm() - distanceKm) < 0.001, "distance " + route.distanceKm() + ", expected " + distanceKm);
        check(route.minutes() == minutes, "minutes " + route.minutes() + ", expected " + minutes);
        check(route.costTenge() == costTenge, "cost " + route.costTenge() + ", expected " + costTenge);
    }

    private static void assertThrows(Class<? extends RuntimeException> expected, Runnable action) {
        try {
            action.run();
        } catch (RuntimeException e) {
            check(expected.isInstance(e), "expected " + expected.getSimpleName() + ", got " + e.getClass().getSimpleName());
            return;
        }
        check(false, "expected " + expected.getSimpleName() + ", nothing was thrown");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void run(String name, Runnable test) {
        try {
            test.run();
            passed++;
            System.out.println("PASS  " + name);
        } catch (AssertionError e) {
            failed++;
            System.out.println("FAIL  " + name + " - " + e.getMessage());
        }
    }
}
