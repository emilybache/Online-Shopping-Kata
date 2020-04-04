import { ModelObject } from "./ModelObject";
import { UnsupportedOperationException } from "./Errors";

/**
 * Items are for sale in a Store (or the central warehouse) and can be put in a Cart
 */
export default class Item implements ModelObject {
    readonly name: string;
    readonly type: string;
    readonly weight: number; // in grams

    constructor(name: string, type: string, weight: number) {
        this.name = name;
        this.type = type;
        this.weight = weight;
    }
    getType() {
        return this.type;
    }

    getName() {
        return this.name;
    }

    getWeight() {
        return this.weight;
    }

    toString() {
        return "Item{" +
                "name='" + this.name + '\'' +
                ", type='" + this.type + '\'' +
                ", weight=" + this.weight +
                '}';
    }

    saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
