public class PushNotificationSender extends NotificationSender{
    //Realisation of Concrete Creators
    @Override
    protected Notification createNotification(){
        return new PushNotification();
    }
    @Override
    protected String getChannelName(){
        return "PUSH";
    }
}
