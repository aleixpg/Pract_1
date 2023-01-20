package Dynamic;

import Main.Actor;

/**
 * http://javahowto.blogspot.com.es/2011/12/java-dynamic-proxy-example.html
 */
public interface TestIF {
    Object setActor (Actor a);

    Object addInsult(String insult);
    Object getInsult();
    Object getAllInsults();
}
