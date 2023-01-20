
public abstract class ActorDecorator implements Actor {

    private Actor actor;

    public ActorDecorator(Actor actor){
        this.actor = actor;
    }

    @Override
    public Message process() {
        return actor.process();
    }

    @Override
    public void send(Message msg) {
        actor.send(msg);
    }

    @Override
    public String getActorName() {
        return actor.getActorName();
    }

}
