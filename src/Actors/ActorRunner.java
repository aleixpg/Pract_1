package Actors;

import Main.*;
import Messages.EndMessage;
import Messages.Message;
import Messages.QuitMessage;
import Observer.EventManager;
import Proxy.ActorProxy;
import Proxy.MainProxy;

import java.util.ArrayList;

public class ActorRunner extends Thread{

    private Actor myActor;
    private ActorProxy myProxy;
    //private final ArrayList<Message> mailbox;

    private final ActorContext actorContext = ActorContext.getInstance();
    private final EventManager eventManager = EventManager.getInstance();


    public ActorRunner(Actor myActor){
        this.myActor = myActor;
        //this.mailbox = myActor.getMailbox();

        this.start();
    }

    @Override
    public void run(){
        String eventMsg = "";

        eventManager.notify("Creation", this.myActor.getActorName(),null);

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //set name to thead for an easyer debugging
        String name = myActor.getActorName()+"->Tread";
        setName(name);

        this.myProxy = actorContext.lookup(myActor.getActorName());
        this.myActor = myProxy.getActor();

        while (true){
            try {
                double a = Math.random()*1000 + 1000;
                sleep((int)a);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (this.myActor.getMailbox().size() > 0) {
                myActor.setSentMessages();

                //Get
                Message msg, newMsg;
                msg = this.myActor.getMailbox().get(0);

                //Delete
                this.myActor.getMailbox().remove(0);

                eventMsg = Main.createMessage(msg, this.myActor.getActorName());
                eventManager.notify("MessageReceived", this.myActor.getActorName(),eventMsg);

                //Process
                newMsg = this.myActor.process(msg);

                //Reply
                Actor to = newMsg.getFrom();
                if (this.myProxy != null){
                    newMsg.setFrom(this.myProxy);
                }else {
                    newMsg.setFrom(this.myActor);
                }

                //Check if End or Kill
                if (!((newMsg instanceof EndMessage)||(newMsg instanceof QuitMessage))) {
                    if (to != null){
                        to.send(newMsg);
                    }
                }

                if (newMsg instanceof QuitMessage) {
                    eventManager.notify("Finalization", this.myActor.getActorName(),null);
                    actorContext.delete(this.myActor.getActorName());
                    //this.stop();
                    this.interrupt();
                    break;  //kill
                }
            }
        }
    }
}
