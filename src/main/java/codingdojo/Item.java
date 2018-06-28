package codingdojo;

/**
 * Items are for sale in a Store (or the central warehouse) and can be put in a Cart
 */
public class Item implements ModelObject {

    protected final String name;
    protected final String type;
    protected final long weight; // in grams

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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

}
