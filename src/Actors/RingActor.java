package Actors;

import Messages.*;
import Main.Actor;
import Main.Main;
import Observer.EventManager;
import Proxy.*;


import java.util.ArrayList;
import java.util.Arrays;

public class InsultActor extends Thread implements Actor {

    private Actor myProxy;
    private String name;
    private ArrayList<Message> mailbox = new ArrayList<>();
    private ArrayList<String> insults = new ArrayList<>(Arrays.asList("feo", "guapo", "alto"));

    private EventManager eventManager = EventManager.getInstance();

    private int sentMessages=0;
    private int receivedMessages=0;
    public InsultActor(){
        //this.start();
        /*
        eventManager.addEvent("Creation");
        eventManager.addEvent("Finalization");
        eventManager.addEvent("IncorrectFinalization");
        eventManager.addEvent("MessageReceived");
        */


        new ActorRunner(this);
    }

    @Override
    public void run(){
        while (true){
            Main.sleep(1);
            if (this.mailbox.size() > 0) {
                //Get
                Message msg, newMsg;
                msg = this.mailbox.get(0);

                //Delete
                this.mailbox.remove(0);

                //Process
                newMsg = process(msg);

                //Reply
                Actor to = newMsg.getFrom();
                if (myProxy != null){
                    newMsg.setFrom(myProxy);
                }else {
                    newMsg.setFrom(this);
                }

                //Check if End or Kill
                if (!(newMsg instanceof EndMessage)) {
                    to.send(newMsg);
                }

                if (newMsg instanceof QuitMessage) {
                    //this.stop();
                    this.interrupt();
                    break;  //kill
                }
            }
        }
    }

    @Override
    public Message process(Message msg) {

        //Process
        if( msg instanceof GetInsultMessage) {
            //Get random
            int size = insults.size();
            int rand = (int)(Math.random() * size);
            String insult = insults.get(rand);
            //msg = new Message(this, "Random insult: "+insult);
            //msg.setBody("Random insult: "+insult);
            msg = new OkMessage(msg.getFrom(),"Random insult: "+insult);
        }
        else if( msg instanceof AddInsultMessage) {
            //Add
            insults.add(msg.getBody());
            //msg = new Message(this, "Succesfuly added insult: "+msg.getBody());
           // msg.setBody("Succesfuly added insult: "+msg.getBody());
            msg = new OkMessage(msg.getFrom(),"Successfully added insult: "+msg.getBody());
        }
        else if( msg instanceof GetAllInsultsMessage) {
            //All
            String allInsults = "";
            boolean first = true;
            for (String elem: insults) {
                if (first) {
                    allInsults += elem;
                    first = false;
                }
                else {
                    allInsults += ", "+elem;
                }
            }
            //msg = new Message(this, "Succesfuly get all insults: " + allInsults);
            //msg.setBody("Succesfuly get all insults: " + allInsults);
            msg = new OkMessage(msg.getFrom(),"Successfully get all insults: " + allInsults);
        }
        else if ( msg instanceof QuitMessage ) {
            msg = new QuitMessage(msg.getFrom(),"I'm suiciding");
        }
        else if ( msg instanceof OkMessage ) {
            msg = new EndMessage(null,null);
        }
        else {
            msg = new OkMessage(msg.getFrom(),"Ok");
        }

        return msg;
    }

    @Override
    public void send(Message msg) {
        this.mailbox.add(msg);
        receivedMessages += 1;
        /*
        String from = "";

        //Type of actor
        if (!(msg.getFrom() instanceof ActorProxy || msg.getFrom() instanceof MainProxy)){
            from += (msg.getFrom() == null) ? "null" : msg.getFrom().getActorName();

            System.out.println("[ " + from + " ] sent a message to [ " + this.name + " ] --> "+msg.getBody());
        }
        */

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
        this.myProxy = myProxy;
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
    public int getSentMessages(){
        return sentMessages;
    }
    @Override
    public void setSentMessages(){
        sentMessages +=1;
    }
    @Override
    public int getReceivedMessages(){
        return receivedMessages;
    }

}
