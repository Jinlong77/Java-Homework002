package event;

import enumeration.EventType;
import model.User;

public abstract class BaseEvent {
    private User user;
    private final EventType eventType;

    public BaseEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public User getUser() {
        return user;
    }

}
