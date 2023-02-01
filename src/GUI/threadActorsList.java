package GUI;

import Actors.HelloWorldActor;
import Actors.InsultActor;
import Main.Actor;
import Main.ActorContext;
import Observer.EventManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

class threadActorsList implements Runnable{

    public JPanel allListedActors;
    public Server server;

    @Override
    public void run() {
        //ActorContext actorContext = ActorContext.getInstance();
        //EventManager eventManager = EventManager.getInstance();

        while (true){
            try {
                Thread.sleep(50);
                //String[] actors = actorContext.getNames();
                String[] actors = server.getAllActorsName();

                //eventManager.monitorAllActors();

                //Clear Jframe
                allListedActors.removeAll();

                //Create all
                for (String actor: actors) {

                    //Create a PAnel
                    JPanel OneActorP = new JPanel(new BorderLayout(10,10));

                    //Get data
                    String type = "";
                    /*
                    Actor OneActor = actorContext.lookup(actor);
                    if (OneActor.getActor() instanceof HelloWorldActor){
                        type = "HelloWorldActor";
                    } else if (OneActor.getActor() instanceof InsultActor) {
                        type = "InsultActor";
                    } else {
                        type = "RingActor";
                    }*/
                    type = server.instanceofActor(actor);

                    //Create a button
                    JButton actorButton = new JButton(actor);
                    actorButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                new OneActorGUI(actor, server);
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });

                    //Create a progress bar
                    JProgressBar progression = new JProgressBar(0,10);
                    //int value = actorContext.lookup(actor).getActor().getMailbox().size();
                    int value = server.getMailboxSize(actor);
                    progression.setValue(value);

                    //add to a panel
                    OneActorP.add(new JLabel(type),BorderLayout.NORTH);
                    OneActorP.add(actorButton,BorderLayout.CENTER);
                    OneActorP.add(progression,BorderLayout.SOUTH);
                    allListedActors.add(OneActorP);


                }

                //Reprint
                allListedActors.revalidate();
                allListedActors.repaint();
            } catch (InterruptedException | RemoteException e) {
                //throw new RuntimeException(e);
                break;
            }
        }
    }
}
