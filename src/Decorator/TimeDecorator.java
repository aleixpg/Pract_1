import Decorator.ActorDecorator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDecorator extends ActorDecorator {

    public TimeDecorator(Actor actor){
        super(actor);
    }

    @Override
    public Message process() {
        //Pre
        SimpleDateFormat formatter= new SimpleDateFormat("'['HH:mm']' ");
        Date date = new Date(System.currentTimeMillis());
        String newBody = formatter.format(date);

        //body
        Message msg = super.process();

        //Post
        newBody += msg.getBody();
        msg.setBody(newBody);

        System.out.println("time decorator");

        return msg;
    }


    @Override
    public void send(Message msg) {
        System.out.println("time decorator");

        SimpleDateFormat formatter= new SimpleDateFormat("'['HH:mm']' ");
        Date date = new Date(System.currentTimeMillis());
        String newBody = formatter.format(date);
        newBody += msg.getBody();

        msg.setBody(newBody);
        System.out.println(msg.getBody());
        super.send(msg);
        //actor.send(msg);
        //this.mailbox.add(msg);
    }

}
