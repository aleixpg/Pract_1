package Messages;

import Main.Actor;

public class Message {

    private Actor from;
    private String body;

    public Message(Actor from, String body) {
        this.from = from;
        this.body = body;
    }

    public Actor getFrom() {
        return from;
    }

    public void setFrom(Actor from) {
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
