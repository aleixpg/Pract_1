package Main;

import Messages.*;

import java.util.ArrayList;

public interface Actor {

    public Message process(Message msg);
    public void send(Message msg);
    public void setActorName(String name);
    public String getActorName();
    public void setMyProxy(Actor myProxy);
    public ArrayList<Message> getMailbox();
    public Actor getActor();
    public int getSentMessages();
    public int getReceivedMessages();
    public void setSentMessages();
}
