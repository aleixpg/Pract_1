package Observer;

import java.util.List;
import java.util.Map;

public class FinalizationListener implements EventListener{

    public FinalizationListener() {}

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        List<String> actorsCreate = (List<String>) map.get("CREATED");
        actorsCreate.add(name+":"+eventType);
        System.out.println("An actor called "+name+" has finalized...");
        actorsCreate = (List<String>) map.get("STOPPED");
        actorsCreate.add(name+":"+eventType);
    }
}
