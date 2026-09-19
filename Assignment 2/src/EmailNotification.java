class EmailNotification implements Notification{
    //concrete product
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending EMAIL to " + recipient + ": " + message);
    }
}