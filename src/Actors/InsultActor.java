import java.util.ArrayList;
import java.util.Arrays;

public class InsultActor extends Thread implements Actor {

    private String name;
    private ArrayList<Message> mailbox = new ArrayList<>();

    private ArrayList<String> insults = new ArrayList<>(Arrays.asList("feo", "guapo", "alto"));

    public InsultActor(String name){
        this.name = name;
        this.start();
    }

    @Override
    public void run(){
        while (true){
            sleep(1);
            if (this.mailbox.size() > 0) {

                //Process
                Message msg = process();

                //Reply
                Actor to = msg.getFrom();
                msg.setFrom(this);
                to.send(msg);
                //System.out.print(to.getActorName());
                //System.out.println(msg.getBody());

                if (msg instanceof QuitMessage) {
                    //this.stop();
                    this.interrupt();
                    break;  //kill
                }
            }
        }
    }

    @Override
    public Message process() {

        //Receive
        Message msg;
        msg = this.mailbox.get(0);

        //Process
        if( msg instanceof GetInsultMessage ) {
            //Get random
            int size = insults.size();
            int rand = (int)(Math.random() * size);
            String insult = insults.get(rand);
            //msg = new Message(this, "Random insult: "+insult);
            msg.setBody("Random insult: "+insult);
        }
        else if( msg instanceof AddInsultMessage ) {
            //Add
            insults.add(msg.getBody());
            //msg = new Message(this, "Succesfuly added insult: "+msg.getBody());
            msg.setBody("Succesfuly added insult: "+msg.getBody());
        }
        else if( msg instanceof GetAllInsultsMessage ) {
            //All
            String allInsults = "";
            boolean first = true;
            for (String elem: insults) {
                if (first) {
                    allInsults += elem;
                    first = false;
                }
                else {
                    allInsults += ", "+elem;
                }
            }
            //msg = new Message(this, "Succesfuly get all insults: " + allInsults);
            msg.setBody("Succesfuly get all insults: " + allInsults);
        }

        //Delete
        this.mailbox.remove(0);

        return msg;
    }

    @Override
    public void send(Message msg) {
        this.mailbox.add(msg);
        System.out.println("[" + msg.getFrom().getActorName() + "] sent a message to [" + this.name + "] --> "+msg.getBody());
    }

    private void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String getActorName() {
        return this.name;
    }

}
