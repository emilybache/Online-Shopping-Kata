import { UnsupportedOperationException } from './Errors';
import Item from './Item';
import { IModelObject } from './ModelObject';
import StoreEvent from './StoreEvent';

/**
 * Represents a physical Store where you can go and buy
 * products and attend events.
 */
export default class Store implements IModelObject {
    private itemsInStock: Map<string, Item> = new Map();
    private readonly name: string;
    private droneDelivery: boolean;

    constructor(name: string, droneDelivery: boolean) {
        this.name = name;
        this.droneDelivery = droneDelivery;
    }

    public addStockedItems(...items: Item[]) {
        for (const item of items) {
            this.itemsInStock.set(item.getName(), item);
        }
    }

    public addStoreEvent(storeEvent: StoreEvent) {
        this.itemsInStock.set(storeEvent.getName(), storeEvent);
    }

    public removeStockedItems(...items: Item[]) {
        for (const item of items) {
            this.itemsInStock.delete(item.getName());
        }
    }

    public hasItem(item: Item) {
        return this.itemsInStock.has(item.getName());
    }

    public getItem(name: string) {
        return this.itemsInStock.get(name);
    }

    public hasDroneDelivery() {
        return this.droneDelivery;
    }

    public toString() {
        return "Store{" +
            "name='" + this.name + "\', " +
            "droneDelivery=" + this.droneDelivery +
            '}';
    }

    public saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

}
