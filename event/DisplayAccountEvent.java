package event;

import static enumeration.EventType.DISPLAY_ACCOUNT;

public class DisplayAccountEvent extends BaseEvent {

    public DisplayAccountEvent() {
        super(DISPLAY_ACCOUNT);
    }
}
