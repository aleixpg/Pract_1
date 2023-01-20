package Observer;

public class FinalizationListener implements EventListener{

    public FinalizationListener() {}

    @Override
    public void update(String eventType, String name, String msg) {
        System.out.println("An actor called "+name+" has finalized...");
    }
}
