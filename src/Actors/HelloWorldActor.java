package Actors;

import Main.Actor;
import Messages.EndMessage;
import Messages.Message;
import Messages.OkMessage;
import Messages.QuitMessage;

import java.io.Serializable;
import java.util.ArrayList;

public class HelloWorldActor implements Actor {

    private Actor myProxy;
    private String name;
    private final ArrayList<Message> mailbox = new ArrayList<>();

    private int sentMessages=0;
    private int receivedMessages=0;

    public HelloWorldActor(){
        new ActorRunner(this);
    }

    @Override
    public Message process(Message msg) {
        if ( msg instanceof QuitMessage ) {
            msg = new QuitMessage(msg.getFrom(),"I'm suiciding");
        }
        else if ((msg instanceof OkMessage)) {
            msg = new EndMessage(null,null);
        }
        else {
            msg = new OkMessage(msg.getFrom(),"Ok");
        }

        return msg;
    }

    @Override
    public void send(Message msg) {
        this.mailbox.add(msg);
        receivedMessages += 1;
    }

    @Override
    public void setActorName(String name) {
        this.name = name;
    }
    @Override
    public String getActorName() {
        return this.name;
    }
    @Override
    public void setMyProxy(Actor myProxy) {
        this.myProxy = myProxy;
    }
    @Override
    public ArrayList<Message> getMailbox() {
        return this.mailbox;
    }
    @Override
    public Actor getActor() {
        return this;
    }
    @Override
    public int getSentMessages(){
        return sentMessages;
    }
    @Override
    public int getReceivedMessages(){
        return receivedMessages;
    }
    @Override
    public void setSentMessages(){
        sentMessages +=1;
    }
}
