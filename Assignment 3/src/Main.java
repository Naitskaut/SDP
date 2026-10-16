import Bridge.CorporateNotificationSender;
import Bridge.EmailNotification;
import Bridge.Notification;
import Bridge.NotificationSender;
import Bridge.PromoNotificationSender;
import Bridge.PushNotification;
import Bridge.SmsNotification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Notification email = new EmailNotification();
        Notification sms = new SmsNotification();
        Notification push = new PushNotification();

        System.out.println("=== Promo sender, channel switched at runtime ===\n");
        NotificationSender promoSender = new PromoNotificationSender(sms);
        promoSender.dispatch("Yeska", "Hello");
        promoSender.setNotification(push);
        promoSender.dispatch("Yeska", "Hello");

        System.out.println("=== Corporate sender, channel switched at runtime ===\n");
        NotificationSender corporateSender = new CorporateNotificationSender(email);
        corporateSender.dispatch("Amir", "Whats up?");
        corporateSender.setNotification(sms);
        corporateSender.dispatch("Amir", "Whats up?");


        try {
            promoSender.setNotification(null);
        } catch (NullPointerException e) {
            System.out.println("Validation works: " + e.getMessage());
        }

        Map<String, Notification> available = new LinkedHashMap<>();
        available.put("email", email);
        available.put("sms", sms);
        available.put("push", push);
        runLiveDemo(promoSender, available);
    }

    //user picks channel while program is running
    private static void runLiveDemo(NotificationSender sender, Map<String, Notification> channels){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Live demo ===");
        while (true) {
            System.out.print("Choose channel " + channels.keySet() + " or exit: ");
            if (!scanner.hasNextLine()) {
                return;
            }
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("exit")) {
                return;
            }
            Notification channel = channels.get(choice);
            if (channel == null) {
                System.out.println("Unknown channel: " + choice);
                continue;
            }
            sender.setNotification(channel);
            sender.dispatch("Yeska", "Hello");
        }
    }
}
