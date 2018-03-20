package codingdojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store implements ModelObject {

    private final Map<String, Item> itemsInStock = new HashMap<>();
    private boolean droneDelivery;

    public Store(List<Item> itemsInStock, boolean droneDelivery) {
        for (Item item: itemsInStock) {
            this.itemsInStock.put(item.getName(), item);
        }
        this.droneDelivery = droneDelivery;
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
}
