package GUI;

import Actors.HelloWorldActor;
import Actors.InsultActor;
import Actors.RingActor;
import Main.Actor;
import Main.ActorContext;
import Messages.*;
import Observer.*;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ServerImpl extends UnicastRemoteObject implements Server{

    static ActorContext actorContext = ActorContext.getInstance();
    static EventManager eventManager = EventManager.getInstance();

    public ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public String spawnHelloWorldActor(String name) throws RemoteException{
        return actorContext.spawnActor(name,new HelloWorldActor()).getActorName();
    }

    @Override
    public String spawnInsultActor(String name) throws RemoteException {
        return actorContext.spawnActor(name,new InsultActor()).getActorName();
    }

    @Override
    public String spawnRingActor(String name) throws RemoteException {
        return actorContext.spawnActor(name,new RingActor()).getActorName();
    }

    @Override
    public String[] getAllActorsName() throws RemoteException {
        return actorContext.getNames();
    }

    @Override
    public int getReceivedMessages(String name) throws RemoteException {
        return actorContext.lookup(name).getActor().getReceivedMessages();
    }

    @Override
    public int getSentMessages(String name) throws RemoteException {
        return actorContext.lookup(name).getActor().getSentMessages();
    }

    @Override
    public String instanceofActor(String name) throws RemoteException {
        Actor OneActor = actorContext.lookup(name).getActor();
        String type = "";
        if (OneActor instanceof HelloWorldActor){
            type = "HelloWorldActor";
        } else if (OneActor instanceof InsultActor) {
            type = "InsultActor";
        } else if (OneActor instanceof RingActor){
            type = "RingActor";
        } else {
            type = "NotFound";
        }
        return type;
    }

    @Override
    public int getMailboxSize(String name) throws RemoteException {
        return actorContext.lookup(name).getActor().getMailbox().size();
    }

    @Override
    public void sendAddInsultMessage(String from, String to, String message) throws RemoteException {
        Actor fromActor = actorContext.lookup(from);
        Actor toActor = actorContext.lookup(to);
        toActor.send(new AddInsultMessage(fromActor, message));
    }

    @Override
    public void sendGetInsultMessage(String from, String to, String message) throws RemoteException {
        Actor fromActor = actorContext.lookup(from);
        Actor toActor = actorContext.lookup(to);
        toActor.send(new GetInsultMessage(fromActor, message));
    }

    @Override
    public void sendGetAllInsultsMessage(String from, String to, String message) throws RemoteException {
        Actor fromActor = actorContext.lookup(from);
        Actor toActor = actorContext.lookup(to);
        toActor.send(new GetAllInsultsMessage(fromActor, message));
    }

    @Override
    public void sendNormalMessage(String from, String to, String message) throws RemoteException {
        Actor fromActor = actorContext.lookup(from);
        Actor toActor = actorContext.lookup(to);
        toActor.send(new NormalMessage(fromActor, message));
    }

    @Override
    public void sendOkMessage(String from, String to, String message) throws RemoteException {
        Actor fromActor = actorContext.lookup(from);
        Actor toActor = actorContext.lookup(to);
        toActor.send(new OkMessage(fromActor, message));
    }

    @Override
    public void sendEndMessage(String from, String to, String message) throws RemoteException {
        Actor fromActor = actorContext.lookup(from);
        Actor toActor = actorContext.lookup(to);
        toActor.send(new EndMessage(fromActor, message));
    }

    @Override
    public void sendQuitMessage(String from, String to, String message) throws RemoteException {
        Actor fromActor = actorContext.lookup(from);
        Actor toActor = actorContext.lookup(to);
        toActor.send(new QuitMessage(fromActor, message));
    }

    @Override
    public void subscribe(EventListener event) throws RemoteException {
        eventManager.subscribe("Creation", event);
        eventManager.subscribe("Finalization", event);
        eventManager.subscribe("MessageReceived", event);

    }

    public static void main(String[] args) throws IOException {

        ServerImpl server = new ServerImpl();

        LocateRegistry.createRegistry(2020);
        Naming.rebind("rmi://localhost:2020/RemoteActorsProgram", server);

        System.out.println("Server running... if you want to stop it, press ENTER.");
        System.in.read();

        //UnicastRemoteObject.unexportObject(server, true);
        System.out.println("Stopped");
    }
}
