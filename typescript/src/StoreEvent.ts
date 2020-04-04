import Item from "./Item";
import Store from "./Store";


/**
 * Ticket to In-store event, eg makeover, eyeshadow masterclass
 * or beauty product launch evening reception
 */
export default class StoreEvent extends Item {
    protected location: Store;

    constructor(name: string, location: Store) {
        super(name, "EVENT", 0);
        this.setLocation(location);
    }

    setLocation(locationStore: Store) {
        this.location = locationStore;
        this.location.addStoreEvent(this);
    }

    toString() {
        return "StoreEvent{" +
                "name='" + this.name + '\'' +
                ", location=" + this.location +
                '}';
    }
}
