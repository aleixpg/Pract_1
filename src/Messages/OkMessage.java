package Messages;

import Main.Actor;

public class QuitMessage extends Message{

    public QuitMessage(Actor from, String body) {
        super(from, body);
    }
}
