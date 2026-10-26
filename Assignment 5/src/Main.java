import Strategy.CarStrategy;
import Strategy.CycleStrategy;
import Strategy.Navigator;
import Strategy.PublicTransportStrategy;
import Strategy.Route;
import Strategy.RouteStrategy;
import Strategy.Trip;
import Strategy.WalkStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

//Client - builds the strategies and gives them to the navigator, the navigator only uses them
public class Main {
    public static void main(String[] args) {
        Map<String, RouteStrategy> strategies = availableStrategies();
        Trip trip = new Trip("AITU", "Baiterek", 6.0);
        Navigator navigator = new Navigator(strategies.get("car"));

        System.out.println("=== " + trip.from() + " -> " + trip.to() + ", strategy switched at runtime ===");
        for (RouteStrategy strategy : strategies.values()) {
            navigator.setStrategy(strategy);
            System.out.println(navigator.planRoute(trip).summary());
        }

        try {
            navigator.setStrategy(null);
        } catch (NullPointerException e) {
            System.out.println("\nValidation works: " + e.getMessage());
        }

        runLiveDemo(navigator, strategies);
    }

    //the only place that knows all concrete strategies
    private static Map<String, RouteStrategy> availableStrategies() {
        Map<String, RouteStrategy> strategies = new LinkedHashMap<>();
        strategies.put("car", new CarStrategy());
        strategies.put("walk", new WalkStrategy());
        strategies.put("cycle", new CycleStrategy());
        strategies.put("bus", new PublicTransportStrategy());
        return strategies;
    }

    //user picks the way of travel while the program is running
    private static void runLiveDemo(Navigator navigator, Map<String, RouteStrategy> strategies) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Live demo ===");
        while (true) {
            System.out.print("Choose transport " + strategies.keySet() + " or exit: ");
            if (!scanner.hasNextLine()) {
                return;
            }
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("exit")) {
                return;
            }
            RouteStrategy strategy = strategies.get(choice);
            if (strategy == null) {
                System.out.println("Unknown transport: " + choice);
                continue;
            }
            navigator.setStrategy(strategy);
            Route route = navigator.planRoute(new Trip("Mega Silk Way", "Khan Shatyr", 3.5));
            System.out.println(route.summary());
        }
    }
}
