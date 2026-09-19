public class SmsNotificationSender extends NotificationSender{
    //Realisation of Concrete Creators
    @Override
    protected Notification createNotification(){
        return new SmsNotification();
    }
    @Override
    protected String getChannelName(){
        return "SMS";
    }
}
