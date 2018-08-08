package codingdojo;


import java.util.HashMap;
import java.util.Map;

/**
 * Allows the OnlineShopping to access data classes
 * and store them in the database
 */
public class Session {


    private final Map<String, ModelObject> session;

    public Session() {
        session = new HashMap<>();
        session.put("CART", new Cart());
        session.put("LOCATION_SERVICE", new LocationService());
    }

    public ModelObject get(String key) {
        return this.session.get(key);
    }

    public void put(String key, ModelObject value) {
        this.session.put(key, value);
    }
    
    public void saveAll() {
        for (String key : session.keySet()) {
            ModelObject entity = session.get(key);
            if (entity != null) {
                entity.saveToDatabase();
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sessionContents = new StringBuffer("\n");
        for (String key: session.keySet()) {
            sessionContents.append(key);
            sessionContents.append("=");
            ModelObject modelObject = session.get(key);
            sessionContents.append(modelObject);
            sessionContents.append("\n");
        }

        return "Session{" +
                sessionContents +
                "}";
    }
}
