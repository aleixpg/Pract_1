package Decorator;

import Main.Actor;
import Messages.Message;

import java.util.ArrayList;


public abstract class ActorDecorator implements Actor {

    private Actor actor;

    public ActorDecorator(Actor actor){
        this.actor = actor;
    }

    @Override
    public Message process(Message msg) {
        return actor.process(msg);
    }

    @Override
    public void send(Message msg) {
        actor.send(msg);
    }

    @Override
    public void setActorName(String name) {
        actor.setActorName(name);
    }
    @Override
    public String getActorName() {
        return actor.getActorName();
    }
    @Override
    public void setMyProxy(Actor myProxy) {
        int x;
    }
    @Override
    public ArrayList<Message> getMailbox() {
        return actor.getMailbox();
    }
    @Override
    public Actor getActor() {
        return this.actor;
    }
    @Override
    public int getSentMessages(){
        return actor.getSentMessages();
    }
    @Override
    public int getReceivedMessages(){
        return actor.getReceivedMessages();
    }
    @Override
    public void setSentMessages(){
        actor.setSentMessages();
    }

}
