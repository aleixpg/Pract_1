package Main;

import Actors.*;
import Messages.*;
import Observer.*;
import Proxy.*;
import Dynamic.*;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args){

        ActorContext actorContext = ActorContext.getInstance();
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());
        eventManager.subscribe("ProxyMessage", new ProxyMessageListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Moya");


        //ActorProxy pere = actorContext.spawnActor("Pere", new HelloWorldActor());
        //ActorProxy pau = actorContext.spawnActor("Pau", new HelloWorldActor());
        //ActorProxy moya = actorContext.spawnActor("Moya", new InsultActor());
        ActorProxy pere = actorContext.spawnActor("Pedro", new InsultActor());
        //ActorProxy moya = actorContext.spawnActor("Moya", new EncryptionDecorator(new InsultActor()));
        //ActorProxy pedro = actorContext.spawnActor("Pedro", new EncryptionDecorator(new InsultActor()));
        //ActorProxy moya = actorContext.spawnActor("Moya", new FirewallDecorator(new EncryptionDecorator(new InsultActor())));
        //ActorProxy pedro = actorContext.spawnActor("Pedro", new FirewallDecorator(new EncryptionDecorator(new InsultActor())));
        ActorProxy moya = actorContext.spawnActor("Moya", new InsultActor());
        //ActorProxy pedro = actorContext.spawnActor("Pedro", new FirewallDecorator(new InsultActor()));
        //ActorProxy dynamic = actorContext.spawnActor("Dyna", new InsultActor());
        //ActorProxy gin = actorContext.spawnActor("Gin", new InsultActor());
        //ActorProxy main = actorContext.spawnActor("Main", new MainActor());
        Actor main = new MainProxy();


        //eventManager.subscribe("Firewall", new FirewallListener());


        System.out.println(eventManager.getNumberofMessages(moya));

        sleep(2);

        InsultService t = (InsultService) Proxy.newProxyInstance(InsultService.class.getClassLoader(),
                new Class<?>[] {InsultService.class},
                new DynamicProxy(new Service()));
        ActorProxy dani = actorContext.spawnActor("Dani", new InsultActor());
        sleep(2);
        t.setActor(dani);
        sleep(2);
        t.addInsult(moya, "Inutil");
        sleep(2);
        t.getInsult(moya);
        sleep(2);
        t.getAllInsults(moya);
        sleep(2);


    }

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
            Thread.currentThread().interrupt();
        }
    }

    public static void pass() {

    }

    public static String createMessage(Message msg, String actorName) {
        String from = "";
        String eventMsg = "";

        //Create message
        from += (msg.getFrom() == null) ? "null" : msg.getFrom().getActorName();
        eventMsg += "[ " + from + " ] sent a message to [ " + actorName + " ] --> "+msg.getBody();

        return eventMsg;
    }

}
