package Factory;

public class EmailNotificationSender extends NotificationSender {
    //Realisation of concrete creator
    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
    @Override
    protected String getChannelName(){
        return "EMAIL";
    }
}