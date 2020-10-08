import { ModelObject } from "./ModelObject";
import Item from "./Item";
import { UnsupportedOperationException } from "./Errors";

/**
 * While shopping online in a Store, the Cart stores the Items you intend to buy
 */
export default class Cart implements ModelObject {
    items: Item[] = [];
    unavailableItems: Item[] = [];

    getItems() {
        return this.items;
    }
    addItem(item: Item) {
        this.items.push(item);
    }
    addItems(items: Item[]) {
        this.items.push(...items);
    }

    markAsUnavailable(item: Item) {
        this.unavailableItems.push(item);
    }

    toString() {
        return "Cart{" +
                "items=" + this.displayItems(this.items) +
                "unavailable=" + this.displayItems(this.unavailableItems) +
                '}';
    }

    private displayItems(items: Item[]) {
        let itemDisplay = "\n";
        for (let item of items) {
            itemDisplay += item.toString();
            itemDisplay += "\n";
        }
        return itemDisplay.toString();
    }

    saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

    getUnavailableItems() {
        return this.unavailableItems;
    }
}
