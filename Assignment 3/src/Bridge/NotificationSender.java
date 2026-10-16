package Bridge;

import java.util.Objects;

public abstract class NotificationSender {
    protected Notification notification;

    protected NotificationSender(Notification notification){
        setNotification(notification);
    }
    public final void setNotification(Notification notification){
        this.notification = Objects.requireNonNull(notification, "Notification channel must not be null");
    }

    public void dispatch(String recipient, String message) {
        notification.send(recipient, format(message));
    }

    protected abstract String format(String message);
}
