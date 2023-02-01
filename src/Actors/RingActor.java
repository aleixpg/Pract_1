package Actors;

import Main.Actor;
import Main.ActorContext;
import Messages.*;
import Observer.EventManager;

import java.util.ArrayList;

public class RingActor implements Actor {

    private Actor myProxy;
    private String name;
    private final ArrayList<Message> mailbox = new ArrayList<>();

    private final EventManager eventManager = EventManager.getInstance();
    private final ActorContext actorContext = ActorContext.getInstance();

    private int sentMessages = 0;
    private int receivedMessages = 0;

    public RingActor(){
        new ActorRunner(this);
    }

    @Override
    public Message process(Message msg) {
        ArrayList<String> ringList = actorContext.getRingActors();
        String a = ringList.get(0);
        //Actor newFrom = actorContext.lookup(a).getActor();
        Actor newFrom = actorContext.lookup(a);

        ringList.remove(0);
        ringList.add(a);

        /*
        int size = ringList.size();
        int rand = (int)(Math.random() * size);
        String actor = ringList.get(rand);

        Actor newFrom = actorContext.lookup(actor).getActor();

        ringList.remove(rand);
        ringList.add(actor);*/

        //Process
        if( msg instanceof NormalMessage) {
            msg = new NormalMessage(newFrom,"Ring");
        }
        else if ( msg instanceof QuitMessage ) {
            msg = new QuitMessage(msg.getFrom(),"I'm suiciding");
        }
        else if ( msg instanceof OkMessage ) {
            msg = new EndMessage(null,null);
        }
        else {
            msg = new NormalMessage(newFrom,"Ok");
        }

        return msg;
    }

    @Override
    public void send(Message msg) {
        this.mailbox.add(msg);
        receivedMessages += 1;

        //this.getMailbox().add(msg);
        //setReceivedMessages(getReceivedMessages() + 1);
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
