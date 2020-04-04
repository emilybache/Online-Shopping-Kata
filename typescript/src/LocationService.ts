import { ModelObject } from "./ModelObject";
import { UnsupportedOperationException } from "./Errors";
import Store from "./Store";

/**
 * The LocationService can tell you if a delivery address is within delivery range
 * of a particular Store. This is a placeholder implementation.
 */
export default class LocationService implements ModelObject {
    isWithinDeliveryRange(store: Store, deliveryAddress: string) {
        return "NEARBY" == deliveryAddress;
    }

    toString(): string {
        return "LocationService";
    }

    saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
