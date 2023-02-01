package Messages;

import Main.Actor;

public class AddInsultMessage extends Message{

    public AddInsultMessage(Actor from, String body) {
        super(from, body);
    }
}
