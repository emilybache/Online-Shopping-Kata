package codingdojo;

/**
 * Ticket to In-store event, eg makeover, eyeshadow masterclass
 * or beauty product launch evening reception
 */
public class StoreEvent extends Item {
    protected Store location;

    public StoreEvent(String name, Store location) {
        super(name, "EVENT", 0);
        this.location = location;
        location.addStoreEvent(this);
    }

    @Override
    public String toString() {
        return "StoreEvent{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

    public void setLocation(Store locationStore) {
        this.location = locationStore;
    }
}
