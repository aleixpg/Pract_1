package Messages;

import Main.Actor;

public class NormalMessage extends Message{

    public NormalMessage(Actor from, String body) {
        super(from, body);
    }
}
