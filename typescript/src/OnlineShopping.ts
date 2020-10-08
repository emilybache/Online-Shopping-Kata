import Session from "./Session";
import Store from "./Store";
import DeliveryInformation from "./DeliveryInformation";
import Item from "./Item";
import Cart from "./Cart";
import LocationService from "./LocationService";

/**
 * The online shopping company owns a chain of Stores selling
 * makeup and beauty products.
 * <p>
 * Customers using the online shopping website can choose a Store then
 * can put Items available at that store into their Cart.
 * <p>
 * If no store is selected, then items are shipped from
 * a central warehouse.
 */
export default class OnlineShopping {
    session: Session;

    constructor(session: Session) {
        this.session = session;
    }

    /**
     * This method is called when the user changes the
     * store they are shopping at in the online shopping
     * website.
     *
     */
    switchStore(storeToSwitchTo: Store) {
        const cart = this.session.get("CART") as Cart;
        const deliveryInformation = this.session.get("DELIVERY_INFO") as DeliveryInformation;
        if (storeToSwitchTo == null) {
            if (cart != null) {
                for (let item of cart.getItems()) {
                    if ("EVENT" === item.getType()) {
                        cart.markAsUnavailable(item);
                    }
                }

            }
            if (deliveryInformation != null) {
                deliveryInformation.setType("SHIPPING");
                deliveryInformation.setPickupLocation(null);
            }
        } else {
            if (cart != null) {
                const newItems: Item[] = [];
                let weight = 0;
                for (let item of cart.getItems()) {
                    if ("EVENT" === item.getType()) {
                        if (storeToSwitchTo.hasItem(item)) {
                            cart.markAsUnavailable(item);
                            newItems.push(storeToSwitchTo.getItem(item.getName())!);
                        } else {
                            cart.markAsUnavailable(item);
                        }
                    } else if (!storeToSwitchTo.hasItem(item)) {
                        cart.markAsUnavailable(item);
                    }
                    weight += item.getWeight();
                }
                for (let item of cart.getUnavailableItems()) {
                    weight -= item.getWeight();
                }

                let currentStore = this.session.get("STORE") as Store;
                if (deliveryInformation != null
                        && deliveryInformation.getType() != null
                        && "HOME_DELIVERY" === deliveryInformation.getType()
                        && deliveryInformation.getDeliveryAddress() != null) {
                    if (!(this.session.get("LOCATION_SERVICE") as LocationService).isWithinDeliveryRange(storeToSwitchTo, deliveryInformation.getDeliveryAddress()!)) {
                        deliveryInformation.setType("PICKUP");
                        deliveryInformation.setPickupLocation(currentStore);
                    } else {
                        deliveryInformation.setTotalWeight(weight);
                        deliveryInformation.setPickupLocation(storeToSwitchTo);
                    }
                } else {
                    if (deliveryInformation != null
                            && deliveryInformation.getDeliveryAddress() != null) {
                        if ((this.session.get("LOCATION_SERVICE") as LocationService).isWithinDeliveryRange(storeToSwitchTo, deliveryInformation.getDeliveryAddress()!)) {
                            deliveryInformation.setType("HOME_DELIVERY");
                            deliveryInformation.setTotalWeight(weight);
                            deliveryInformation.setPickupLocation(storeToSwitchTo);
                        }
                    }
                }
                for (let item of newItems) {
                    cart.addItem(item);
                }
            }
        }
        this.session.put("STORE", storeToSwitchTo);
        this.session.saveAll();
    }

    toString() {
        return "OnlineShopping{\n"
                + "session=" + this.session + "\n}";
    }
}
