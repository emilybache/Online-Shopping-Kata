import Item from "./Item";
import Store from "./Store";

/**
 * Ticket to In-store event, eg makeover, eye shadow master class
 * or beauty product launch evening reception.
 */
export default class StoreEvent extends Item {
    protected location!: Store;

    constructor(name: string, location: Store) {
        super(name, "EVENT", 0);
        this.setLocation(location);
    }

    public setLocation(locationStore: Store) {
        this.location = locationStore;
        this.location.addStoreEvent(this);
    }

    public toString() {
        return "StoreEvent{" +
            "name='" + this.name + '\'' +
            ", location=" + this.location +
            '}';
    }
}
