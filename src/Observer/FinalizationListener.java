package Observer;

public class CreationListener implements EventListener{

    //public CreationListener() {}

    @Override
    public void notifyCreation(String eventType, String name) {
        System.out.println("An actor called "+name+" has been created...");
    }
}
