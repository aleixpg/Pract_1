package Observer;

import Main.Actor;
import Main.ActorContext;
import Proxy.ActorProxy;

import java.util.List;
import java.util.Map;

public class MessageReceivedListener implements EventListener{

    public MessageReceivedListener() {}

    private final EventManager eventManager = EventManager.getInstance();
    private final ActorContext actorContext = ActorContext.getInstance();

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        List<String> actorsCreate = (List<String>) map.get("CREATED");
        actorsCreate.add(name+":"+eventType);
        System.out.println("An actor called "+name+" has received a message...\n└--> "+msg);
        actorsCreate = (List<String>) map.get("STOPPED");
        actorsCreate.add(name+":"+eventType);

        //Actualize traffic
        //If I am monitorizing this actor do:
        if (eventManager.getActorsList().contains(name)){
            List<String> actorsLow = eventManager.getTrafficList().get("LOW");
            List<String> actorsMid = eventManager.getTrafficList().get("MEDIUM");
            List<String> actorsHigh = eventManager.getTrafficList().get("HIGH");

            //Clear name on any of 3 lists
            if (actorsLow.contains(name)){
                actorsLow.remove(name);
            }
            if (actorsMid.contains(name)){
                actorsMid.remove(name);
            }
            if (actorsHigh.contains(name)){
                actorsHigh.remove(name);
            }

            //Add on correct list
            Actor thisActor = actorContext.lookup(name).getActor();
            int recv = thisActor.getReceivedMessages();

            if (recv < 5){
                actorsLow.add(name);
            } else if (recv < 15) {
                actorsMid.add(name);
            } else if (15 < recv) {
                actorsHigh.add(name);
            }

        }


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
