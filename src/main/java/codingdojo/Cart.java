package codingdojo;

import java.util.ArrayList;
import java.util.List;

public class Cart implements ModelObject {
    ArrayList<Item> items = new ArrayList<>();
    public List<Item> getItems() {
        return items;
    }
    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }
}
