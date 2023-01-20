package Observer;

import Messages.Message;

public interface ActorListener {

    public void notifyCreation(String name);
    public void notifyFinalization(String name);
    public void notifyIncorrectFinalization(String name);
    public void notifyMessageReceived(Message message, String name);

}
