package Observer;

import Observer.EventListener;

import javax.swing.*;
import java.util.Map;

public class MessageReceivedListenerGUI implements EventListener {
    JTextArea text;

    public MessageReceivedListenerGUI(JTextArea text) {
        this.text = text;
    }

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        //System.out.println("An actor called "+name+" has been created...");
        text.append("An actor called "+name+" has received a message...\n└--> "+msg+"\n");
    }
}
