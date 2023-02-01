package Messages;

import Main.Actor;

public class EndMessage extends Message{

    public EndMessage(Actor from, String body) {
        super(from, body);
    }
}
