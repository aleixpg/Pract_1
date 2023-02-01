package Main;

import Actors.InsultActor;
import Actors.RingActor;
import Messages.Message;
import Observer.EventManager;
import Proxy.ActorProxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActorContext {

    //The single instance
    private static final ActorContext actorContext = new ActorContext();
    private final Map<String, ActorProxy> ActorsRegistry = new HashMap<>();
    private final EventManager eventManager = EventManager.getInstance();
    private final ArrayList<String> ringActors = new ArrayList<>();



    //Private constructor makes impossible to create foreign instances
    private ActorContext() {}

    //The only way to get the instance
    public static ActorContext getInstance(){
        return actorContext;
    }


    //Spawning an actor
    public ActorProxy spawnActor(String name, Actor actor) {
        int randNum = 0;


        //check if any equal is added
        while (ActorsRegistry.containsKey(name)){
            randNum = (int)(Math.random() * (9 + 1));
            name += randNum;
        }

        //set the same name on actor
        actor.setActorName(name);

        //Add ring actor
        if (actor instanceof RingActor){
            ringActors.add(actor.getActorName());
        }

        //create its proxy
        ActorProxy actorProxy = new ActorProxy(actor);

        //add on registry
        ActorsRegistry.put(name, actorProxy);

        //eventManager.notify("Creation", name,null);

        //return that actor
        return actorProxy;
    }

    //lookup
    public ActorProxy lookup(String name){
        ActorProxy actor;

        //Get from registry
        if (ActorsRegistry.containsKey(name)){
            actor = ActorsRegistry.get(name);
        } else {
            actor = null;
        }

        return actor;
    }

    //Getting all names
    public String[] getNames(){
        String list[] = ActorsRegistry.keySet().toArray(new String[0]);

        return list;
    }

    public void delete(String name){
        if (ActorsRegistry.containsKey(name)){
            ActorsRegistry.remove(name);
        }
    }

    public ArrayList<String> getRingActors(){
        return this.ringActors;
    }

}
