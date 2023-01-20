package GUI;

import Main.ActorContext;
import Observer.EventListener;

import javax.swing.*;
import java.util.Map;

class FinalizationListenerGUI implements EventListener {
    JTextArea text;

    public FinalizationListenerGUI(JTextArea text) {
        this.text = text;
    }

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        //System.out.println("An actor called "+name+" has been created...");
        text.append("An actor called "+name+" has finalized...\n");

        //ActorContext actorContext = ActorContext.getInstance();
        //actorContext.delete(name);
    }
}
