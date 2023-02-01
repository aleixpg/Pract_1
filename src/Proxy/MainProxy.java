package Proxy;

import Main.Actor;
import Main.Main;
import Messages.Message;
import Messages.NormalMessage;
import Messages.QuitMessage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainProxy implements Actor {
    private String name;
    private ArrayList<Message> mailbox = new ArrayList<>();

    public MainProxy(){
        this.name = "MainProxy";
    }

    @Override
    public Message process(Message msg) {
        //Message msg = new NormalMessage(null,"null");
        String from = "";

        while (this.mailbox.size() == 0){
            Main.sleep(1);
        }
        //if (this.mailbox.size() > 0) {
        msg = this.mailbox.get(0);
        this.mailbox.remove(0);

        if ((msg.getFrom() instanceof ActorProxy || msg.getFrom() instanceof MainProxy)){
            from = "PROXY ";
        }
        from += (msg.getFrom() == null) ? "null" : msg.getFrom().getActorName();
        //System.out.println("[ RESULT ][ PROXY " + this.name + " ] recv a message from [ " + from + " ] --> "+msg.getBody());

        //msg = realActor.process();

        return msg;
    }

    @Override
    public void send(Message msg) {
        this.mailbox.add(msg);

        String from = "";

        //Type of actor
        if ((msg.getFrom() instanceof ActorProxy || msg.getFrom() instanceof MainProxy)){
            from = "PROXY ";
            from += (msg.getFrom() == null) ? "null" : msg.getFrom().getActorName();
            //System.out.println("[ " + from + " ] sent a message to [ PROXY " + this.name + " ] --> "+msg.getBody());
        }

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
        //int x;
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
