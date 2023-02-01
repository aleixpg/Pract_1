package Actors;

import Main.Actor;
import Messages.*;
import Observer.EventManager;

import java.util.ArrayList;
import java.util.Arrays;

public class InsultActor implements Actor {

    private Actor myProxy;
    private String name;
    private final ArrayList<Message> mailbox = new ArrayList<>();
    private final ArrayList<String> insults = new ArrayList<>(Arrays.asList("feo", "guapo", "alto"));

    private final EventManager eventManager = EventManager.getInstance();

    private int sentMessages=0;
    private int receivedMessages=0;

    public InsultActor(){
        new ActorRunner(this);
    }

    @Override
    public Message process(Message msg) {

        //Process
        if( msg instanceof GetInsultMessage) {
            //Get random
            int size = insults.size();
            int rand = (int)(Math.random() * size);
            String insult = insults.get(rand);
            msg = new OkMessage(msg.getFrom(),"Random insult: "+insult);
        }
        else if( msg instanceof AddInsultMessage) {
            //Add
            insults.add(msg.getBody());
            msg = new OkMessage(msg.getFrom(),"Successfully added insult: "+msg.getBody());
        }
        else if( msg instanceof GetAllInsultsMessage) {
            //All
            String allInsults = "";
            boolean first = true;
            for (String elem: insults) {
                if (first) {
                    allInsults += elem;
                    first = false;
                }
                else {
                    allInsults += ", "+elem;
                }
            }
            msg = new OkMessage(msg.getFrom(),"Successfully get all insults: " + allInsults);
        }
        else if ( msg instanceof QuitMessage ) {
            msg = new QuitMessage(msg.getFrom(),"I'm suiciding");
        }
        else if ( msg instanceof OkMessage ) {
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
    public void setSentMessages(){
        sentMessages +=1;
    }
    @Override
    public int getReceivedMessages(){
        return receivedMessages;
    }

}
