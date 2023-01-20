import java.util.HashMap;
import java.util.Map;

public class ActorContext {

    //The single instance
    private static ActorContext actorContext = new ActorContext();
    Map<String, Actor> ActorsRegistry = new HashMap<>();

    //Private constructor makes impossible to create foreign instances
    private ActorContext() {}

    //The only way to get the instance
    public static ActorContext getInstance(){
        return actorContext;
    }

    //Spawning an actor
    public Actor spawnActor(String name, Actor actor) {
        //add on registry
        ActorsRegistry.put(name, actor);

        //return that actor
        return actor;
    }

    //lookup
    public void lookup(String name){
        //Get from registry
        Actor actor = ActorsRegistry.get(name);
    }

    //Getting all names
    public String[] getNames(){
        String list[] = ActorsRegistry.keySet().toArray(new String[0]);

        return list;
    }

}
