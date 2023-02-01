package GUI;

import Main.Actor;
import Observer.EventListener;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {

    public String spawnHelloWorldActor(String name) throws RemoteException;
    public String spawnInsultActor(String name) throws RemoteException;
    public String spawnRingActor(String name) throws RemoteException;
    public String[] getAllActorsName() throws RemoteException;
    public int getReceivedMessages(String name) throws RemoteException;
    public int getSentMessages(String name) throws RemoteException;
    public String instanceofActor(String name) throws RemoteException;
    public int getMailboxSize(String name) throws RemoteException;
    public void sendAddInsultMessage(String from, String to, String message) throws RemoteException;
    public void sendGetInsultMessage(String from, String to, String message) throws RemoteException;
    public void sendGetAllInsultsMessage(String from, String to, String message) throws RemoteException;
    public void sendNormalMessage(String from, String to, String message) throws RemoteException;
    public void sendOkMessage(String from, String to, String message) throws RemoteException;
    public void sendEndMessage(String from, String to, String message) throws RemoteException;
    public void sendQuitMessage(String from, String to, String message) throws RemoteException;
    public void subscribe(EventListener event) throws RemoteException;
}
