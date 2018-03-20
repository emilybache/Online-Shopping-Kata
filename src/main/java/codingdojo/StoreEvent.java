package codingdojo;

/**
 * Ticket to In-store event, eg makeover, eyeshadow masterclass
 * or beauty product launch evening reception
 */
public class StoreEvent extends Item {
    protected Store location;

    public StoreEvent(String name, Store location) {
        super(name, "EVENT", 0);
        setLocation(location);
    }

    public void setLocation(Store locationStore) {
        this.location = locationStore;
        location.addStoreEvent(this);
    }

    @Override
    public String toString() {
        return "StoreEvent{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }


}
