package Factory;

class PushNotification implements Notification {
    //concrete product
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending PUSH to " + recipient + ": " + message );
    }

}
