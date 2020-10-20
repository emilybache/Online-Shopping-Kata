import { UnsupportedOperationException } from "./Errors";
import { IModelObject } from "./ModelObject";
import Store from "./Store";

/**
 * The LocationService can tell you if a delivery address is within delivery range
 * of a particular Store. This is a place holder implementation.
 */
export default class LocationService implements IModelObject {
    public isWithinDeliveryRange(store: Store, deliveryAddress: string) {
        return "NEARBY" === deliveryAddress;
    }

    public toString(): string {
        return "LocationService";
    }

    public saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
