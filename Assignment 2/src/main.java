public class main {
    public static void main(String[] args) {
        BrandFactory promoFactory = new MarketingPromoFactory();
        Client promoClient = new Client(promoFactory);
        String promoMessage = promoClient.compose("Hello");

        NotificationSender testSms = new SmsNotificationSender();
        testSms.dispatch("Yeska", promoMessage);


        BrandFactory factory = new CorporateBrandFactory();
        Client client = new Client(factory);
        String finalMessage = client.compose("Whats up?");

        NotificationSender testEmail = new EmailNotificationSender();
        testEmail.dispatch("Amir", finalMessage);


        NotificationSender testPush = new PushNotificationSender();
        testPush.dispatch("AGAG", "WEWESES");
    }
}
