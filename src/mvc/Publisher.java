package mvc;
/*
    Edits:
        Kyle 3/5: Created file, pasted in given code
 */

import java.util.*;

public class Publisher {
    private List<Subscriber> subscribers = new LinkedList<Subscriber>();

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            s.update();
        }
    }
}
