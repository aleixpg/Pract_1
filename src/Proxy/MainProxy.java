package Proxy;

import Main.Actor;
import Messages.Message;

import java.util.ArrayList;

public class ActorProxy implements Actor {

    private String name;
    private ArrayList<Message> mailbox = new ArrayList<>();

    private Actor realActor;

    public ActorProxy(Actor actor){
        this.realActor = actor;
    }

    @Override
    public Message process() {
        Message msg;

        /*try {
            msg = this.mailbox.get(0);
            this.mailbox.remove(0);
        } catch (Exception e){
            msg = new Message(this,"null");
        }*/
        msg = realActor.process();

        return msg;
    }

    @Override
    public void send(Message msg) {
        //this.mailbox.add(msg);
        if (realActor == null) {
            /*switch (this){
                case (Actors.HelloWorldActor) realActor: {
                    realActor = new Actors.HelloWorldActor();
                    break;
                }
            }*/
        }
        realActor.send(msg);
    }

    @Override
    public String getActorName() {
        return this.name;
    }
}
