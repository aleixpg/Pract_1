import java.util.ArrayList;

public class HelloWorldActor extends Thread implements Actor {

    private String name;
    private ArrayList<Message> mailbox = new ArrayList<>();

    public HelloWorldActor(String name){
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
                //System.out.println(msg.getBody());

                if (msg instanceof QuitMessage) {
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
