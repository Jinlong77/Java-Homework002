package event.publisher;

import event.BaseEvent;
import event.listener.EventListener;

public class EventPublisher {
    private final EventListener[] listeners = new EventListener[1000];
    private int size = 0;

    public void addListener(EventListener listener) {
        if (size < listeners.length) {
            listeners[size++] = listener;
        }
    }

    public void notifyListeners(BaseEvent event) {
        for (EventListener listener : listeners) {
            if (listener != null) {
                listener.onEvent(event);
                break;
            }
        }
    }

    public void removeAllListeners() {
        for (int i = 0; i < size; i++) {
            listeners[i] = null;
        }
        size = 0;
    }

    public void removeListener(EventListener listener) {
        for (int i = 0; i < size; i++) {
            if (listeners[i] == listener) {

                for (int j = i; j < size - 1; j++) {
                    listeners[j] = listeners[j + 1];
                }
                listeners[size - 1] = null;
                size--;
                return;
            }
        }
    }

}
