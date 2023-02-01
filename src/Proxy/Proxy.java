package Proxy;

import Messages.Message;

public interface Proxy {

    public Message process(Message msg);
    public void send(Message msg);
    public String getActorName();

}
