import Store from "./Store";
import { ModelObject } from "./ModelObject";
import { UnsupportedOperationException } from "./Errors";

/**
 * This class contains the information about how the customer would like to
 * have the contents of their shopping cart delivered to them.
 */
export default class DeliveryInformation implements ModelObject {
    private type: string;
    private deliveryAddress: string;
    private pickupLocation: Store;
    private weight: number;

    public DeliveryInformation(type: string, pickupLocation: Store, weight: number) {
        this.type = type;
        this.pickupLocation = pickupLocation;
        this.weight = weight;
    }

    setType(type: string) {
        this.type = type;
    }

    getType() {
        return this.type;
    }

    setDeliveryAddress(deliveryAddress: string) {
        this.deliveryAddress = deliveryAddress;
    }

    getDeliveryAddress() {
        return this.deliveryAddress;
    }

    setPickupLocation(store: Store) {
        this.pickupLocation = store;
    }

    setTotalWeight(weight: number) {
        this.weight = weight;
    }

    toString() {
        return "DeliveryInformation{" + "\n" +
                "type='" + this.type + '\'' + "\n" +
                "deliveryAddress='" + this.deliveryAddress + '\'' + "\n" +
                "pickupLocation=" + this.pickupLocation + "\n" +
                "weight=" + this.weight + "\n" +
                '}';
    }

    saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
