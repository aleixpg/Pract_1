package Messages;

import Main.Actor;

public class OkMessage extends Message{

    public OkMessage(Actor from, String body) {
        super(from, body);
    }
}
