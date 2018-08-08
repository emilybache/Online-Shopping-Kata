package codingdojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a physical Store where you can go and buy
 * products and attend events.
 */
public class Store implements ModelObject {

    private final Map<String, Item> itemsInStock = new HashMap<>();
    private final String name;
    private boolean droneDelivery;

    public Store(String name, boolean droneDelivery) {
        this.name = name;
        this.droneDelivery = droneDelivery;
    }
    public void addStockedItems(Item... items) {
        for (Item item: items) {
            this.itemsInStock.put(item.getName(), item);
        }
    }

    public void addStoreEvent(StoreEvent storeEvent) {
        this.itemsInStock.put(storeEvent.getName(), storeEvent);
    }

    public void removeStockedItems(Item... items) {
        for (Item item: items) {
            this.itemsInStock.remove(item.getName());
        }
    }

    public boolean hasItem(Item item) {
        return itemsInStock.containsKey(item.getName());
    }

    public Item getItem(String name) {
        return itemsInStock.get(name);
    }

    public boolean hasDroneDelivery() {
        return droneDelivery;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + "\', " +
                "droneDelivery=" + droneDelivery +
                '}';
    }

    @Override
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

}
