package Decorator;

import Main.Actor;
import Main.ActorContext;
import Messages.Message;
import Messages.OkMessage;
import Observer.EventManager;

public class FirewallDecorator extends ActorDecorator {

    /*
    The FirewallDecorator will only let process messages whose sender is a valid Actor
    registered in the ActorContext. It will stop all messages coming from a Proxy
     */

    private final ActorContext actorContext = ActorContext.getInstance();
    private final EventManager eventManager = EventManager.getInstance();

    public FirewallDecorator(Actor actor){
        super(actor);
    }

    @Override
    public Message process(Message msg) {
        //Pre-funcionalitats

        Boolean valid = false;

        //get the sender
        String sender = msg.getFrom().getActorName();

        //get registered actors
        String[] registeredActors = actorContext.getNames();

        //check if is valid
        for (String actor: registeredActors) {
            if (actor.equals(sender)){
                valid = true;
                break;
            }
        }

        //body
        Message newMsg;

        if (valid){
            newMsg = super.process(msg);
        } else {
            eventManager.notify("Firewall", this.getActorName(), msg.getBody());
            Message firewall = new OkMessage(null,"");
            newMsg = super.process(firewall);
        }

        //Post-funcionaliats

        return newMsg;
    }


    @Override
    public void send(Message msg) {
        super.send(msg);
    }

}
