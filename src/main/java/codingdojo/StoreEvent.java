package codingdojo;

/**
 * Ticket to In-store event, eg wine tasting or book signing
 */
public class StoreEvent extends Item {
    private Store sellingStore;
    private Store location;

    public StoreEvent(String name, Store location) {
        super(name, "EVENT", 0);
        this.location = location;
    }

    public void setBillingData(Store store) {
        this.sellingStore = store;
    }


    public void setLocation(Store locationStore) {
        this.location = locationStore;
    }
}
