package Dynamic;

import Main.Actor;
import Proxy.ActorProxy;

/**
 * http://javahowto.blogspot.com.es/2011/12/java-dynamic-proxy-example.html
 */
public interface InsultService {
    Object setActor (Actor a);

    Object addInsult(ActorProxy act, String insult);
    Object getInsult(ActorProxy act);
    Object getAllInsults(ActorProxy act);
}
