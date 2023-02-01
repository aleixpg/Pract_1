package GUI;

import Actors.*;
import Main.*;
import Observer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.Serial;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;

public class ActorsGUI extends JFrame implements EventListener, Remote {

    //ActorContext actorContext = ActorContext.getInstance();
    EventManager eventManager = EventManager.getInstance();
    private final Server server;

    @Serial
    private static final long serialVersionUID = 1L;
    private JButton helloWorldActor;
    //private JLabel spawnLabel = new JLabel("Click the actor you want to spawn:");
    private JLabel spawnLabel;
    private JButton insultActor;
    private JButton ringActor;
    private JTextField actorname;
    private JLabel listedActors;
    private JPanel actorButtons;
    private JTextArea textArea1;
    private JPanel firstLine;
    private JPanel secondLine;
    private JPanel allListedActors;
    private JPanel doubleFirstLine;
    private JScrollPane scrollTextArea;

    private ActorsGUI(String title) throws MalformedURLException, NotBoundException, RemoteException {
        super(title);

        //Connecting to the server
        server = (Server)Naming.lookup("rmi://localhost:2020/RemoteActorsProgram");

        this.setLayout(new GridLayout(3,1));

        doubleFirstLine.setLayout(new GridLayout(2,1));

        //First Line
        actorButtons.setLayout(new FlowLayout());
        actorButtons.add(helloWorldActor);
        actorButtons.add(insultActor);
        actorButtons.add(ringActor);

        firstLine.add(spawnLabel); //Text
        firstLine.add(actorname); //Get name
        firstLine.add(actorButtons); //Button
        //this.add(firstLine);
        doubleFirstLine.add(firstLine);

        //Second Line
        //secondLine.add(listActors); //button
        secondLine.add(listedActors); //Text
        //this.add(secondLine);
        doubleFirstLine.add(secondLine);
        this.add(doubleFirstLine);

        //third line
        this.add(allListedActors);

        //fourth line
        scrollTextArea = new JScrollPane(textArea1);
        //textArea1.setLineWrap(true);
        textArea1.setEditable(false); // set textArea non-editable
        scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollTextArea);



        this.setLocation(800, 500);
        this.setSize(1000,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //Thread for listing actors
        threadActorsList p = new threadActorsList();
        p.allListedActors = allListedActors;
        p.server = server;
        Thread t = new Thread(p);
        t.start();

        //ListenersObserver

        /*
        eventManager.subscribe("Creation", new CreationListenerGUI(textArea1));
        eventManager.subscribe("Finalization", new FinalizationListenerGUI(textArea1));
        eventManager.subscribe("MessageReceived", new MessageReceivedListenerGUI(textArea1));
        */
        //server.subscribe(this);

        scrollTextArea.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });

        helloWorldActor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String name = actorContext.spawnActor(actorname.getText(),new HelloWorldActor()).getActorName();

                String name = "";
                try {
                    name = server.spawnHelloWorldActor(actorname.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(null, "Hello "+name+"!",
                        "Welcome", JOptionPane.INFORMATION_MESSAGE);
                textArea1.append("New HelloWorld Actor has been created: "+name+"\n");
            }
        });

        insultActor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String name = actorContext.spawnActor(actorname.getText(),new InsultActor()).getActorName();

                String name = "";
                try {
                    name = server.spawnInsultActor(actorname.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(null, "Hello "+name+"!",
                        "Welcome", JOptionPane.INFORMATION_MESSAGE);
                textArea1.append("New Insult Actor has been created: "+name+"\n");
            }
        });

        ringActor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String name = actorContext.spawnActor(actorname.getText(),new RingActor()).getActorName();

                String name = "";
                try {
                    name = server.spawnRingActor(actorname.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(null, "Hello "+name+"!",
                        "Welcome", JOptionPane.INFORMATION_MESSAGE);
                textArea1.append("New Ring Actor has been created: "+name+"\n");
            }
        });
    }

    public static void main(String[] args) throws NotBoundException, RemoteException, MalformedURLException {

        //Registry registry = LocateRegistry.getRegistry();
        //server = (Server)registry.lookup("RemoteDataPointHandler");

        //server = (Server)Naming.lookup("rmi://localhost:2020/RemoteActorsProgram");

        new ActorsGUI("Actors");
    }

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        System.out.println("An actor called "+name+" has been created...");
        textArea1.append("An actor called "+name+" has received a message...\n└--> "+msg+"\n");
    }
}

