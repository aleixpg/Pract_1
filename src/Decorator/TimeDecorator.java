package Decorator;

import Main.Actor;
import Messages.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDecorator extends ActorDecorator {

    public TimeDecorator(Actor actor){
        super(actor);
    }

    @Override
    public Message process(Message msg) {
        return super.process(msg);
    }

    @Override
    public void send(Message msg) {
        //Pre
        SimpleDateFormat formatter= new SimpleDateFormat("'['HH:mm']' ");
        Date date = new Date(System.currentTimeMillis());
        String newBody = formatter.format(date);
        newBody += msg.getBody();

        msg.setBody(newBody);
        System.out.println(msg.getBody());

        //body
        super.send(msg);

        //Post
    }

}
