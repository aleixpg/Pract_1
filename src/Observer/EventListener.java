package Observer;

import java.io.Serializable;
import java.util.Map;

public interface EventListener {
    void update(String eventType, String name, String msg, Map map);
}
