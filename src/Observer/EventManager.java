package Observer;

import Main.Actor;
import Main.ActorContext;

import java.io.Serializable;
import java.util.*;

public class EventManager{

    //The single instance
    private static final EventManager eventManager = new EventManager();
    //Listeners
    private final Map<String, List<EventListener>> listeners = new HashMap<>();
    //EventLog
    private Map<String, List<String>> eventLog = new HashMap<>();
    //Actors
    private final ArrayList<String> actorsList = new ArrayList<>();
    //traffic
    private final Map<String, List<String>> traffic = new HashMap<>();

    private ActorContext actorContext = ActorContext.getInstance();




    private EventManager() {
        eventLog.put("CREATED", new ArrayList<>());
        eventLog.put("STOPPED", new ArrayList<>());
        eventLog.put("ERROR", new ArrayList<>());
        addEvent("Creation");
        addEvent("Finalization");
        addEvent("IncorrectFinalization");
        addEvent("MessageReceived");
        addEvent("ProxyMessage");
        addEvent("Firewall");
        traffic.put("LOW", new ArrayList<>());
        traffic.put("MEDIUM", new ArrayList<>());
        traffic.put("HIGH", new ArrayList<>());
    }
    public static EventManager getInstance(){
        return eventManager;
    }

    /*
    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }
    */

    public void addEvent(String event){
        this.listeners.put(event, new ArrayList<>());
    }

    public Map getTraffic(){


        List<String> actorsLow = traffic.get("LOW");
        List<String> actorsMid = traffic.get("MEDIUM");
        List<String> actorsHigh = traffic.get("HIGH");
        String[] list = actorContext.getNames();
        System.out.println("Name of all actors:");
        int i = 0;
        for (String name: list) {
            i = actorContext.lookup(name).getMailbox().size();
            if (i < 5){
                actorsLow.add(name);
            } else if (5 <= i || i < 15) {
                actorsMid.add(name);
            } else if (15 < i) {
                actorsHigh.add(name);
            }
        }
        return traffic;
    }

    public int getNumberofMessages(Actor a){
        ActorContext actorContext = ActorContext.getInstance();
        return actorContext.lookup(a.getActorName()).getMailbox().size();
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, String name, String msg) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            if (actorsList.contains(name)){
                listener.update(eventType, name, msg, eventLog);
            }
        }
    }

    public void monitorActor(String name){
        actorsList.add(name);
    }

    public Map getMapLog(){
        return eventLog;
    }

    public void setMapLog(Map map){
         this.eventLog = map;
    }

    public void monitorAllActors(){
        actorsList.removeAll(actorsList);
        //Assertions.assertEquals(0, actorsList.size());
        actorsList.addAll(Arrays.asList(ActorContext.getInstance().getNames()));

        /*
        for (String name: ActorContext.getInstance().getNames()) {
            actorsList.add(name);
        }
        */
    }

    public ArrayList<String> getActorsList() {
        return actorsList;
    }

    public Map<String, List<String>> getTrafficList(){
        return traffic;
    }
}
