package codingdojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows the OnlineShopping to access data classes
 * and store them in the database
 */
public class WebSession {


    private final Map<String, ModelObject> session;

    public WebSession() {
        session = new HashMap<>();
        setCart(new Cart());
        setLocationService(new LocationService());
    }

    public ModelObject get(String key) {
        return this.session.get(key);
    }

    public void put(String key, ModelObject value) {
        this.session.put(key, value);
    }

    public void clear() {
        this.session.clear();
    }

    public Cart getCart() {
        return (Cart) session.get("CART");
    }

    public void setCart(Cart cart) {
        session.put("CART", cart);
    }

    public LocationService getLocationService() {
        return (LocationService) session.get("LOCATION_SERVICE");
    }

    public void setLocationService(LocationService locationService) {
        session.put("LOCATION_SERVICE", locationService);
    }

    public Store getCurrentStore() {
        return (Store) session.get("STORE");
    }

    public void setCurrentStore(Store currentStore) {
        session.put("STORE", currentStore);
    }

    public DeliveryInformation getDeliveryInfo() {
        return (DeliveryInformation) session.get("DELIVERY_INFO");
    }

    public void setDeliveryInfo(DeliveryInformation deliveryInfo) {
        session.put("DELIVERY_INFO", deliveryInfo);
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
        return "WebSession{" +
                "session=" + session +
                '}';
    }
}
