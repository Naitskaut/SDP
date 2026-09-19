class SmsNotification implements Notification{
    //concrete product
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message );
    }

}