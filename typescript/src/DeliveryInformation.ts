import { UnsupportedOperationException } from "./Errors";
import { IModelObject } from "./ModelObject";
import Store from "./Store";

/**
 * This class contains the information about how the customer would like to
 * have the contents of their shopping cart delivered to them.
 */
export default class DeliveryInformation implements IModelObject {
    private type: string;
    private deliveryAddress?: string;
    private pickUpLocation: Store | null;
    private weight: number;

    constructor(type: string, pickUpLocation: Store | null, weight: number) {
        this.type = type;
        this.pickUpLocation = pickUpLocation;
        this.weight = weight;
    }

    public setType(type: string) {
        this.type = type;
    }

    public getType() {
        return this.type;
    }

    public setDeliveryAddress(deliveryAddress: string) {
        this.deliveryAddress = deliveryAddress;
    }

    public getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public setPickUpLocation(store: Store | null) {
        this.pickUpLocation = store;
    }

    public setTotalWeight(weight: number) {
        this.weight = weight;
    }

    public toString() {
        return "DeliveryInformation{" + "\n" +
            "type='" + this.type + '\'' + "\n" +
            "deliveryAddress='" + this.deliveryAddress + '\'' + "\n" +
            "pickUpLocation=" + (this.pickUpLocation ? this.pickUpLocation.toString() : "null") + "\n" +
            "weight=" + this.weight + "\n" +
            '}';
    }

    public saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
