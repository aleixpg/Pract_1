public interface Actor {

    public Message process();
    public void send(Message msg);
    public String getActorName();
}
