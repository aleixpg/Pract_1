package Main;

import Messages.Message;

import java.util.ArrayList;

public abstract class ActorAbstract implements Actor {

    private Actor myProxy;
    private String name;
    private final ArrayList<Message> mailbox = new ArrayList<>();

    private int sentMessages = 0;
    private int receivedMessages = 0;



    @Override
    public void setActorName(String name) {this.name = name;}
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
    public void setSentMessages(int sentMessages) {
        this.sentMessages = sentMessages;
    }
    @Override
    public int getReceivedMessages(){
        return receivedMessages;
    }
    public void setReceivedMessages(int receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

}
