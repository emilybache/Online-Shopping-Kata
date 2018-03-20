package codingdojo;

/**
 * Items are for sale in a Store and can be put in a Cart
 */
public class Item {

    private final String name;
    private final String type;
    private final long weight; // in grams

    public Item(String name, String type, long weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
    }
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public long getWeight() {
        return weight;
    }

}
