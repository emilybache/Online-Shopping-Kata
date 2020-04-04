import {ModelObject} from './ModelObject'
import Item from './Item';
import StoreEvent from './StoreEvent';
import { UnsupportedOperationException } from './Errors';

/**
 * Represents a physical Store where you can go and buy
 * products and attend events.
 */
export default class Store implements ModelObject {
    private itemsInStock: Map<string, Item> = new Map();
    private readonly name: string;
    private droneDelivery: boolean;

    constructor(name: string, droneDelivery: boolean) {
        this.name = name;
        this.droneDelivery = droneDelivery;
    }
    addStockedItems(...items: Item[]) {
        for (let item of items) {
          this.itemsInStock.set(item.getName(), item);
        }
    }

    addStoreEvent(storeEvent: StoreEvent) {
        this.itemsInStock.set(storeEvent.getName(), storeEvent);
    }

    removeStockedItems(...items: Item[]) {
        for (let item of items) {
            this.itemsInStock.delete(item.getName());
        }
    }

    hasItem(item: Item) {
        return this.itemsInStock.has(item.getName());
    }

    getItem(name: string) {
        return this.itemsInStock.get(name);
    }

    hasDroneDelivery() {
        return this.droneDelivery;
    }

    toString() {
        return "Store{" +
                "name='" + this.name + "\', " +
                "droneDelivery=" + this.droneDelivery +
                '}';
    }

    saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

}
