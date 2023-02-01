package Observer;

import java.util.List;
import java.util.Map;

public class ProxyMessageListener implements EventListener{

    public ProxyMessageListener() {}

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        List<String> actorsCreate = (List<String>) map.get("CREATED");
        actorsCreate.add(name+":"+eventType);
        System.out.println(name+" actor's PROXY has received a message...\n└--> "+msg);
        actorsCreate = (List<String>) map.get("STOPPED");
        actorsCreate.add(name+":"+eventType);
        //System.out.println("└--> "+msg);

        /*
        String from = "";

        if (message.getFrom() instanceof ActorProxy) {
            from += "PROXY ";
        }
        from += (message.getFrom() == null) ? "null" : message.getFrom().getActorName();
        System.out.println("[ " + from + " ] sent a message to [ " + name + " ] --> "+message.getBody());
        */
    }
}
