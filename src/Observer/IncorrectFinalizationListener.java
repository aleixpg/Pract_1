package Observer;

import java.util.List;
import java.util.Map;

public class IncorrectFinalizationListener implements EventListener{

    public IncorrectFinalizationListener() {}

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        List<String> actorsCreate = (List<String>) map.get("CREATED");
        actorsCreate.add(name+":"+eventType);
        System.out.println("An actor called "+name+" had been suffering an incorrect finalization...");
        actorsCreate = (List<String>) map.get("ERROR");
        actorsCreate.add(name+":"+eventType);
    }
}
