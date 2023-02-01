package Proxy;

import Main.Actor;
import Main.Main;
import Messages.Message;
import Observer.EventManager;

import java.io.Serializable;
import java.util.ArrayList;

public class ActorProxy implements Actor {

    private String name;
    // final ArrayList<Message> mailbox = new ArrayList<>();

    private final Actor realActor;

    private final EventManager eventManager = EventManager.getInstance();

    public ActorProxy(Actor actor){
        this.realActor = actor;
        this.name = actor.getActorName();
        this.realActor.setMyProxy(this);
    }

    @Override
    public Message process(Message msg) {
        return realActor.process(msg);
    }

    @Override
    public void send(Message msg) {
        String from = "";
        String eventMsg = "";

        realActor.send(msg);

        //create message
        eventMsg = Main.createMessage(msg, this.realActor.getActorName());
        eventManager.notify("ProxyMessage", this.realActor.getActorName(),eventMsg);
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
        Main.pass();
    }
    @Override
    public ArrayList<Message> getMailbox() {
        return new ArrayList<>();
    }
    @Override
    public Actor getActor() {
        return this.realActor;
    }
    @Override
    public int getSentMessages() {
        Main.pass();
        return 0;
    }
    @Override
    public int getReceivedMessages() {
        Main.pass();
        return 0;
    }
    @Override
    public void setSentMessages() {
        Main.pass();
    }
}
