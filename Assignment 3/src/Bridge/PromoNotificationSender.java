package Bridge;

import AbstractFactory.Client;
import AbstractFactory.MarketingPromoFactory;

public class PromoNotificationSender extends NotificationSender {
    //Refined Abstraction - styles message with promo brand from Abstract Factory
    private final Client client = new Client(new MarketingPromoFactory());

    public PromoNotificationSender(Notification notification){
        super(notification);
    }

    @Override
    protected String format(String message){
        return client.compose(message);
    }
}
