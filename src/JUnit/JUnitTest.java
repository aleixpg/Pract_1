package JUnit;

//import static org.junit.Assert;
//import org.junit.*;
//import org.junit.Assert;
// static org.assertj.core.api.Assertions.assertThat;

import Decorator.*;
import Actors.*;
import Messages.*;
import Observer.*;
import Proxy.*;
import Dynamic.*;
import Main.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
//import static org.junit.Assert.*;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class JUnitTest {
    //JUnit call this method one time before all tests
    @BeforeClass
    public static void setUp(){
        //Place code here for any set up required prior to tests
    }
    @AfterClass
    public static void finished(){
        //Place code here for any clean up that should be done after tests are finished
    }
    @Test
    public void MainProxyTest(){
        //Code the test
        ActorContext actorContext = ActorContext.getInstance();
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Moya");

        //Actors
        ActorProxy moya = actorContext.spawnActor("Moya", new InsultActor());
        Actor main = new MainProxy();

        eventManager.subscribe("ProxyMessage", new ProxyMessageListener());
        Main.sleep(2);

        //Start main proxy app
        moya.send(new GetInsultMessage(main,"Give me an insult!"));
        Message msg = main.process(null);
        System.out.println("[ RESULT ] "+msg.getBody());

        assertTrue("There isn't a OkMessage response.", msg instanceof OkMessage );

        //kill em
        moya.send(new QuitMessage(null,"kill"));
        main.send(new QuitMessage(null,"kill"));

        Main.sleep(2);

    }
    @Test
    public void QuitMessageTest(){
        //Code the test
        ActorContext actorContext = ActorContext.getInstance();
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Moya");

        ActorProxy moya = actorContext.spawnActor("Moya", new InsultActor());
        ActorProxy pedro = actorContext.spawnActor("Pedro", new InsultActor());

        eventManager.subscribe("ProxyMessage", new ProxyMessageListener());
        Main.sleep(2);

        //Start quitmessage app
        moya.send(new QuitMessage(null,"kill"));
        pedro.send(new QuitMessage(null,"kill"));

        Main.sleep(4);

        assertTrue("There aren't 0 actors.", actorContext.getNames().length == 0 );

        //kill em
        moya.send(new QuitMessage(null,"kill"));
        pedro.send(new QuitMessage(null,"kill"));

        Main.sleep(2);

    }
    @Test
    public void InsultTest(){
        //Code the test
        ActorContext actorContext = ActorContext.getInstance();
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Moya");

        ActorProxy moya = actorContext.spawnActor("Moya", new InsultActor());
        ActorProxy pedro = actorContext.spawnActor("Pedro", new InsultActor());

        eventManager.subscribe("ProxyMessage", new ProxyMessageListener());
        Main.sleep(2);

        //Start insult app
        moya.send(new GetInsultMessage(pedro,"Give me an insult!"));
        moya.send(new AddInsultMessage(pedro,"jodido"));
        moya.send(new GetAllInsultsMessage(pedro,"Give me all insults"));

        Main.sleep(10);

        assertTrue("There aren't 1 or more actors.", actorContext.getNames().length > 1 );

        //kill em
        moya.send(new QuitMessage(null,"kill"));
        pedro.send(new QuitMessage(null,"kill"));

        Main.sleep(2);
    }
    @Test
    public void RingAppTest(){
        //Code the test
        ActorContext actorContext = ActorContext.getInstance();
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Pedro1");
        eventManager.monitorActor("Pedro2");
        eventManager.monitorActor("Pedro3");
        eventManager.monitorActor("Pedro4");

        ActorProxy pedro = actorContext.spawnActor("Pedro", new RingActor());
        ActorProxy pedro1 = actorContext.spawnActor("Pedro1", new RingActor());
        ActorProxy pedro2 = actorContext.spawnActor("Pedro2", new RingActor());
        ActorProxy pedro3 = actorContext.spawnActor("Pedro3", new RingActor());
        ActorProxy pedro4 = actorContext.spawnActor("Pedro4", new RingActor());

        eventManager.subscribe("ProxyMessage", new ProxyMessageListener());
        Main.sleep(2);

        //Start ring app
        pedro4.send(new NormalMessage(pedro3,"Ring"));
        Main.sleep(30);

        assertTrue("There aren't 5 actors on medium traffic.", eventManager.getTrafficList().get("MEDIUM").size() == 5 );
        //List<String> actorsMed = eventManager.getTraffic().get("MEDIUM");
        //assertTrue("There aren't 4 or more actors.", actorsMed.size() > 4 );

        //kill em
        pedro.send(new QuitMessage(null,"kill"));
        pedro1.send(new QuitMessage(null,"kill"));
        pedro2.send(new QuitMessage(null,"kill"));
        pedro3.send(new QuitMessage(null,"kill"));
        pedro4.send(new QuitMessage(null,"kill"));

        Main.sleep(2);
    }
    @Test
    public void TrafficTest(){
        //Code the test
        ActorContext actorContext = ActorContext.getInstance();
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Moya");

        ActorProxy moya = actorContext.spawnActor("Moya", new HelloWorldActor());
        ActorProxy pedro = actorContext.spawnActor("Pedro", new HelloWorldActor());

        eventManager.subscribe("ProxyMessage", new ProxyMessageListener());
        Main.sleep(2);

        //Start insult app
        moya.send(new NormalMessage(pedro,"HelloWorld!"));
        moya.send(new NormalMessage(pedro,"HelloWorld!"));
        moya.send(new NormalMessage(pedro,"HelloWorld!"));
        moya.send(new NormalMessage(pedro,"HelloWorld!"));
        moya.send(new NormalMessage(pedro,"HelloWorld!"));
        moya.send(new NormalMessage(pedro,"HelloWorld!"));
        moya.send(new NormalMessage(pedro,"HelloWorld!"));

        Main.sleep(5);

        //kill em
        moya.send(new QuitMessage(null,"kill"));
        pedro.send(new QuitMessage(null,"kill"));

        Main.sleep(5);

        Map<String, List<String>> traffic = eventManager.getTrafficList();
        System.out.println(traffic);

        assertTrue("There isn't a map there.", traffic.get("MEDIUM").size() == 2 );
    }
    @Test
    public void PingPongTest(){
        //Code the test
        ActorContext actorContext = ActorContext.getInstance();

        EventManager eventManager = EventManager.getInstance();
        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Moya");

        ActorProxy moya = actorContext.spawnActor("Moya", new InsultActor());
        ActorProxy pedro = actorContext.spawnActor("Pedro", new InsultActor());
        System.out.println("");
        Main.sleep(3);
        int i = 0;
        while (i < 10){
            pedro.send(new NormalMessage(moya,"Hello Pedro"));
            moya.send(new NormalMessage(pedro,"Hello Moya"));
            System.out.println("");
            i++;
        }

        //When arrived to 10, they send OK messages to confirm it is done
        assertTrue("There aren't 4 or more actors.", actorContext.getNames().length == 2 );
        //List<String> actorsMed = eventManager.getTraffic().get("MEDIUM");
        //assertTrue("There aren't 4 or more actors.", actorsMed.size() > 4 );

        //kill em
        moya.send(new QuitMessage(null,"kill"));
        pedro.send(new QuitMessage(null,"kill"));

        Main.sleep(2);
    }

    @Test
    public void DynamicTest(){
        //Code the test
        ActorContext actorContext = ActorContext.getInstance();
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Moya");
        eventManager.monitorActor("Dani");

        ActorProxy moya = actorContext.spawnActor("Moya", new InsultActor());
        ActorProxy pedro = actorContext.spawnActor("Pedro", new InsultActor());

        InsultService t = (InsultService) Proxy.newProxyInstance(InsultService.class.getClassLoader(),
                new Class<?>[] {InsultService.class},
                new DynamicProxy(new Service()));
        ActorProxy dani = actorContext.spawnActor("Dani", new InsultActor());
        Main.sleep(2);
        t.setActor(dani);
        Main.sleep(2);
        t.addInsult(moya, "Inutil");
        Main.sleep(2);
        t.getInsult(moya);
        Main.sleep(2);
        t.getAllInsults(moya);
        Main.sleep(2);
        //kill em
        moya.send(new QuitMessage(null,"kill"));
        pedro.send(new QuitMessage(null,"kill"));
        dani.send(new QuitMessage(null,"kill"));
        Main.sleep(5);


    }

    @Test
    public void DecoratorTest(){
        //Code the test
        ActorContext actorContext = ActorContext.getInstance();
        EventManager eventManager = EventManager.getInstance();

        eventManager.subscribe("Creation", new CreationListener());
        eventManager.subscribe("Finalization", new FinalizationListener());
        eventManager.subscribe("IncorrectFinalization", new IncorrectFinalizationListener());
        eventManager.subscribe("MessageReceived", new MessageReceivedListener());

        eventManager.monitorActor("Pedro");
        eventManager.monitorActor("Moya");

        //Actors
        ActorProxy pedro = actorContext.spawnActor("Pedro", new EncryptionDecorator(new TimeDecorator(new HelloWorldActor())));
        ActorProxy moya = actorContext.spawnActor("Moya", new EncryptionDecorator(new HelloWorldActor()));
        Main.sleep(2);

        //Start main proxy app
        pedro.send(new NormalMessage(moya,"hola"));

        assertTrue("Decorator is not working well", new NormalMessage(moya,"hola").getBody() == "hola");

        //kill em
        moya.send(new QuitMessage(null,"kill"));
        pedro.send(new QuitMessage(null,"kill"));

        Main.sleep(2);
    }

}
