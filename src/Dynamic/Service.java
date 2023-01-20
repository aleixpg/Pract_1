package Dynamic;

import Main.Actor;
import Messages.AddInsultMessage;
import Messages.GetAllInsultsMessage;
import Messages.GetInsultMessage;

/**
 * http://javahowto.blogspot.com.es/2011/12/java-dynamic-proxy-example.html
 */

public class TestImpl implements TestIF {
    Actor a;
    public Actor getAllInsults() {
        a.send(new GetAllInsultsMessage(a,"Give me all insults!"));
        return a;
    }
    public Actor addInsult(String insult) {
        a.send(new AddInsultMessage(a,insult));
        return a;
    }

    public Actor getInsult(){
        a.send(new GetInsultMessage(a, "Give me an insult!"));
        return a;
    }

    @Override
    public Object setActor(Actor a) {
        this.a = a;
        System.out.println("The actor is "+ a.getActorName());
        return a;
    }
}