package Bridge;

public class PushNotification implements Notification {
    //Concrete Implementor - push shows only a short preview
    @Override
    public void send(String recipient, String message) {
        String preview = ChannelLimits.shortenToOneLine(message, ChannelLimits.PUSH_MAX_LENGTH);
        MessageBox.print("PUSH", recipient, preview);
    }

}
