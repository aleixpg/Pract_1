package Dynamic;

/**
 * http://javahowto.blogspot.com.es/2011/12/java-dynamic-proxy-example.html
 *
 */

import Main.Actor;
import Main.*;
import Messages.AddInsultMessage;
import Messages.GetAllInsultsMessage;
import Messages.GetInsultMessage;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Actor a;
    private Object testImpl;

    public DynamicProxy(Object impl) {
        this.testImpl = impl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        try
        {
            Actor c;
            System.out.println("");
            System.out.println("Before method " + method.getName() + ":");
            switch (method.getName()){
                case "getAllInsults":
                    c = (Actor) args[0];
                    a.send(new GetAllInsultsMessage(c,"Give me all insults!"));
                    Main.sleep(4);
                    break;
                case "addInsult":
                    c = (Actor) args[0];
                    a.send(new AddInsultMessage(c,(String) args[1]));
                    Main.sleep(4);
                    break;
                case "getInsult":
                    c = (Actor) args[0];
                    a.send(new GetInsultMessage(c, "Give me an insult!"));
                    Main.sleep(4);
                    break;
                case "setActor":
                    c = (Actor) args[0];
                    this.a = c;
                    System.out.println("The actor is "+ c.getActorName());
                    Main.sleep(4);
                    break;
            }
            System.out.println("After method " + method.getName() + ":");
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            return a;
        }
    }
}