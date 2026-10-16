package Bridge;

public class SmsNotification implements Notification {
    //Concrete Implementor - sms is one plain line with length limit
    @Override
    public void send(String recipient, String message) {
        String text = ChannelLimits.shortenToOneLine(message, ChannelLimits.SMS_MAX_LENGTH);
        MessageBox.print("SMS", recipient, text);
    }

}
