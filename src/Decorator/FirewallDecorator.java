package Decorator;

import Main.Actor;
import Messages.Message;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class LambdaFirewallDecorator extends ActorDecorator {

    public LambdaFirewallDecorator(Actor actor){
        super(actor);
    }

    @Override
    public Message process() {
        //Pre-funcionalitats

        //body
        Message msg = super.process();

        //Post-funcionaliats

        return msg;
    }


    @Override
    public void send(Message msg) {
        super.send(msg);
    }

}
