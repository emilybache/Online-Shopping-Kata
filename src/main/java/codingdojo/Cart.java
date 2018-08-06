package codingdojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * While shopping online in a Store, the Cart stores the Items you intend to buy
 */
public class Cart implements ModelObject {
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Item> unavailableItems = new ArrayList<>();
    private long weight;

    public List<Item> getItems() {
        return items;
    }
    public void addItem(Item item) {
        this.items.add(item);
    }
    public void addItems(Collection<Item> items) {
        this.items.addAll(items);
    }

    public void markAsUnavailable(Item item) {
        this.unavailableItems.add(item);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + displayItems(items) +
                "unavailable=" + displayItems(unavailableItems) +
                '}';
    }

    private String displayItems(List<Item> items) {
        StringBuffer itemDisplay = new StringBuffer("\n");
        for (Item item : items) {
            itemDisplay.append(item.toString());
            itemDisplay.append("\n");
        }
        return itemDisplay.toString();
    }

    @Override
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

    public Collection<Item> getUnavailableItems() {
        return unavailableItems;
    }

    void markEventsUnavailable() {
        for (Item item : getItems()) {
            if ("EVENT".equals(item.getType())) {
                markAsUnavailable(item);
            }
        }
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getWeight() {
        return weight;
    }

    void doSwitchStore(Store storeToSwitchTo) {
        if (storeToSwitchTo == null) {
            markEventsUnavailable();
        } else {
            ArrayList<Item> newItems = new ArrayList<>();
            long weight1 = 0;
            for (Item item : getItems()) {
                if ("EVENT".equals(item.getType())) {
                    if (storeToSwitchTo.hasItem(item)) {
                        markAsUnavailable(item);
                        newItems.add(storeToSwitchTo.getItem(item.getName()));
                    } else {
                        markAsUnavailable(item);
                    }
                } else if (!storeToSwitchTo.hasItem(item)) {
                    markAsUnavailable(item);
                }
                weight1 += item.getWeight();
            }
            for (Item item : getUnavailableItems()) {
                weight1 -= item.getWeight();
            }
            for (Item item : newItems) {
                addItem(item);
            }
            setWeight(weight1);
        }
    }
}
