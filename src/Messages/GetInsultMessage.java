package Messages;

import Main.Actor;

public class GetInsultMessage extends Message{

    public GetInsultMessage(Actor from, String body) {
        super(from, body);
    }
}
