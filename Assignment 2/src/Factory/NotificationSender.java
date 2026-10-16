package Factory;

public abstract class NotificationSender {
    //Abstract creator with factory methods
    protected abstract Notification createNotification();
    protected abstract String getChannelName();

    public void dispatch(String recipient, String message) {
        String channel = getChannelName();

        System.out.println("┌─ " + channel + " " + "─".repeat(30));
        System.out.println("│ To: " + recipient);

        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println("│ " + line);
        }

        System.out.println("└" + "─".repeat(33));

        Notification notification = createNotification();

        System.out.println();
    }
}
