package Messages;

import Main.Actor;

public class GetAllInsultsMessage extends Message{

    public GetAllInsultsMessage(Actor from, String body) {
        super(from, body);
    }
}
