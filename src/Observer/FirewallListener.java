package Observer;

import java.util.List;
import java.util.Map;

public class FirewallListener implements EventListener{

    public FirewallListener() {}

    @Override
    public void update(String eventType, String name, String msg, Map map) {
        List<String> actorsCreate = (List<String>) map.get("CREATED");
        actorsCreate.add(name+":"+eventType);
        System.out.println("WARNING! A firewall from actor called "+name+" has stopped a message... -> "+msg);
        actorsCreate = (List<String>) map.get("STOPPED");
        actorsCreate.add(name+":"+eventType);
    }
}
