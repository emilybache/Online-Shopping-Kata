import { UnsupportedOperationException } from "./Errors";
import { IModelObject } from "./ModelObject";

/**
 * Items are for sale in a Store (or the central warehouse) and can be put in a Cart.
 */
export default class Item implements IModelObject {
    protected readonly name: string;
    private readonly type: string;
    private readonly weight: number; // in grams

    constructor(name: string, type: string, weight: number) {
        this.name = name;
        this.type = type;
        this.weight = weight;
    }

    public getType() {
        return this.type;
    }

    public getName() {
        return this.name;
    }

    public getWeight() {
        return this.weight;
    }

    public toString() {
        return "Item{" +
            "name='" + this.name + '\'' +
            ", type='" + this.type + '\'' +
            ", weight=" + this.weight +
            '}';
    }

    public saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
