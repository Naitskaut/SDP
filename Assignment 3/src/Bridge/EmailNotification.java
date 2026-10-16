package Bridge;

public class EmailNotification implements Notification {
    //Concrete Implementor - email has no limits, shows full message
    @Override
    public void send(String recipient, String message) {
        MessageBox.print("EMAIL", recipient, message);
    }
}
