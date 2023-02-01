package Decorator;

import Main.Actor;
import Messages.Message;

public class LambdaFirewallDecorator extends ActorDecorator {

    /*
    Create a LambdaFirewallDecorator that accepts closures to filter
    the messages than can be received using an AddClosureMessage.
     */

    public LambdaFirewallDecorator(Actor actor){
        super(actor);
    }

    @Override
    public Message process(Message msg) {
        //Pre-funcionalitats

        //body
        Message newMsg = super.process(msg);

        //Post-funcionaliats

        return newMsg;
    }

    @Override
    public void send(Message msg) {
        super.send(msg);
    }

}
