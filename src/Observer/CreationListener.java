package Observer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreationListener implements EventListener{

    public CreationListener() {}

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        List<String> actorsCreate = (List<String>) map.get("CREATED");
        actorsCreate.add(name+":"+eventType);
        System.out.println("An actor called "+name+" has been created...");
        actorsCreate = (List<String>) map.get("STOPPED");
        actorsCreate.add(name+":"+eventType);
    }
}
