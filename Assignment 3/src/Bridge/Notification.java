package Bridge;

public interface Notification {
    //Implementor - delivery channel, knows nothing about brands
    void send(String recipient, String message);
}
