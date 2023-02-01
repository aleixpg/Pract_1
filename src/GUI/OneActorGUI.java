package GUI;

import Main.Actor;
import Main.ActorContext;
import Messages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class OneActorGUI extends JFrame {

    //ActorContext actorContext = ActorContext.getInstance();
    private final Server server;

    private JComboBox listDestination;
    private JLabel selectDestination;
    private JPanel destination;
    private JLabel selectMessage;
    private JComboBox listMessages;
    private JPanel message;
    private JTextField textMessage;
    private JLabel messageTextLabel;
    private JPanel textMessageLine;
    private JButton sendButton;
    private JPanel buttons;
    private JPanel statisticsLayout;
    private JLabel numSent;
    private JLabel numRecv;


    public OneActorGUI(String name, Server server) throws RemoteException {
        super(name);

        //setting server
        this.server = server;

        this.setLayout(new GridLayout(5, 1));

        //Getting actor list
        //String[] actorsList = actorContext.getNames();
        String[] actorsList = server.getAllActorsName();
        for (String actor: actorsList) {
            listDestination.addItem(actor);
        }


        //First Line
        //destination.setLayout(new FlowLayout());
        destination.add(selectDestination);
        destination.add(listDestination);
        this.add(destination);

        //Getting message list
        String messagesType[] = {   "AddInsult",
                                    "GetInsult",
                                    "GetAllInsults",
                                    "Normal",
                                    "Ok",
                                    "End",
                                    "Quit"};
        for (String message: messagesType) {
            listMessages.addItem(message);
        }

        //Second line
        message.add(selectMessage);
        message.add(listMessages);
        this.add(message);

        //Third line
        textMessageLine.add(messageTextLabel);
        textMessageLine.add(textMessage);
        this.add(textMessageLine);

        //Fourth line
        statisticsLayout.setLayout(new GridLayout(1,2));
        //int recv = actorContext.lookup(name).getActor().getReceivedMessages();
        int recv = server.getReceivedMessages(name);
        numRecv.setText("Number of received messages: "+recv);
        //int sent = actorContext.lookup(name).getActor().getSentMessages();
        int sent = server.getSentMessages(name);
        numSent.setText("Number of sent messages: "+sent);
        statisticsLayout.add(numSent);
        statisticsLayout.add(numRecv);
        this.add(statisticsLayout);

        //Fifth line
        buttons.add(sendButton);
        this.add(buttons);

        this.setLocation(900, 600);
        this.setSize(425, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        //Making message
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //Actualize stats
                    //int recv = actorContext.lookup(name).getActor().getReceivedMessages();
                    int recv = server.getReceivedMessages(name);
                    numRecv.setText("Number of received messages: "+recv);
                    //int sent = actorContext.lookup(name).getActor().getSentMessages();
                    int sent = server.getSentMessages(name);
                    numSent.setText("Number of sent messages: "+sent);


                    //MessageSending

                    //Actor fromActor = actorContext.lookup(name);
                    //Actor toActor = actorContext.lookup(listDestination.getSelectedItem().toString());
                    String toActor = listDestination.getSelectedItem().toString();
                    String messageText = textMessage.getText();

                    String messageType = listMessages.getSelectedItem().toString();

                    //Switch case sending message
                    switch (messageType) {
                        case "AddInsult" -> {
                            server.sendAddInsultMessage(name, toActor, messageText);
                        }
                        case "GetInsult" -> {
                            server.sendGetInsultMessage(name, toActor, messageText);
                        }
                        case "GetAllInsults" -> {
                            server.sendGetAllInsultsMessage(name, toActor, messageText);
                        }
                        case "Normal" -> {
                            server.sendNormalMessage(name, toActor, messageText);
                        }
                        case "Ok" -> {
                            server.sendOkMessage(name, toActor, messageText);
                        }
                        case "End" -> {
                            server.sendEndMessage(name, toActor, messageText);
                        }
                        case "Quit" -> {
                            server.sendQuitMessage(name, toActor, messageText);
                        }
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

                /*
                if (toActor != null){
                    switch (messageType) {
                        case "AddInsult" -> {
                            toActor.send(new AddInsultMessage(fromActor, messageText));
                        }
                        case "GetInsult" -> {
                            toActor.send(new GetInsultMessage(fromActor, messageText));
                        }
                        case "GetAllInsults" -> {
                            toActor.send(new GetAllInsultsMessage(fromActor, messageText));
                        }
                        case "Normal" -> {
                            toActor.send(new NormalMessage(fromActor, messageText));
                        }
                        case "Ok" -> {
                            toActor.send(new OkMessage(fromActor, messageText));
                        }
                        case "End" -> {
                            toActor.send(new EndMessage(fromActor, messageText));
                        }
                        case "Quit" -> {
                            toActor.send(new QuitMessage(fromActor, messageText));
                        }
                    }
                }*/
            }
        });
    }

    public static void main(String[] args) {
        //new ActorsGUI("Actors");
    }

}
