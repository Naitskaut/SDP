package Bridge;

import AbstractFactory.Client;
import AbstractFactory.CorporateBrandFactory;

public class CorporateNotificationSender extends NotificationSender {
    //Refined Abstraction - styles message with corporate brand from Abstract Factory
    private final Client client = new Client(new CorporateBrandFactory());

    public CorporateNotificationSender(Notification notification){
        super(notification);
    }

    @Override
    protected String format(String message){
        return client.compose(message);
    }
}
