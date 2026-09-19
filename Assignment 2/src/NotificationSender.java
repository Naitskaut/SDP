public abstract class NotificationSender {
    //Abstract creator with factory methods
    protected abstract Notification createNotification();
    protected abstract String getChannelName();

    public void dispatch(String recipient, String message){
        System.out.println("Preparing to send via " + getChannelName() + "...");

        Notification notification = createNotification();
        notification.send(recipient, message);

        System.out.println("Notification sent via " + getChannelName() + ".");
    }
}
