package Dynamic;

import Main.Actor;
import Messages.AddInsultMessage;
import Messages.GetAllInsultsMessage;
import Messages.GetInsultMessage;
import Proxy.ActorProxy;

/**
 * http://javahowto.blogspot.com.es/2011/12/java-dynamic-proxy-example.html
 */

public class Service implements InsultService {
    Actor a;
    public Actor getAllInsults(ActorProxy act) {
        a.send(new GetAllInsultsMessage(act,"Give me all insults!"));
        return a;
    }
    public Actor addInsult(ActorProxy act, String insult) {
        a.send(new AddInsultMessage(a,insult));
        return a;
    }

    public Actor getInsult(ActorProxy act){
        a.send(new GetInsultMessage(act, "Give me an insult!"));
        return a;
    }

    public Object setActor(Actor a) {
        this.a = a;
        System.out.println("The actor is "+ a.getActorName());
        return a;
    }
}