import { UnsupportedOperationException } from "./Errors";
import Item from "./Item";
import { IModelObject } from "./ModelObject";

/**
 * While shopping online in a Store, the Cart stores the Items you intend to buy.
 */
export default class Cart implements IModelObject {
    private items: Item[] = [];
    private unavailableItems: Item[] = [];

    public getItems() {
        return this.items;
    }

    public addItem(item: Item) {
        this.items.push(item);
    }

    public addItems(items: Item[]) {
        this.items.push(...items);
    }

    public markAsUnavailable(item: Item) {
        this.unavailableItems.push(item);
    }

    public toString() {
        return "Cart{" +
            "items=" + this.displayItems(this.items) +
            "unavailable=" + this.displayItems(this.unavailableItems) +
            '}';
    }

    public saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

    public getUnavailableItems() {
        return this.unavailableItems;
    }

    private displayItems(items: Item[]) {
        let itemDisplay = "\n";
        for (const item of items) {
            itemDisplay += item.toString();
            itemDisplay += "\n";
        }
        return itemDisplay.toString();
    }
}
