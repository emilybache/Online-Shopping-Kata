package codingdojo;

import java.util.Map;

/**
 * The online shopping company owns a chain of stores. Customers
 * using the online shopping website can choose a Store then
 * can put Items available at that store into their Cart.
 *
 * If no store is selected, then items are shipped from
 * a central warehouse.
 *
 */
public class OnlineShopping {

    private Map<String, ModelObject> webSession;

    public OnlineShopping(Map<String, ModelObject> webSession) {
        this.webSession = webSession;
    }

    /**
     * This method is called when the user changes the
     * store they are shopping at in the online shopping
     * website.
     *
     * @param storeToSwitchTo
     */
    public void switchStore(Store storeToSwitchTo) {
        Cart cart = (Cart)webSession.get("CART");

        DeliveryInformation deliveryInformation = (DeliveryInformation)webSession.get("ORDER_DELIVERY");
        if (storeToSwitchTo == null) {
            if (cart != null) {
                for (Item item : cart.getItems()) {
                    if ("EVENT".equals(item.getType())) {
                        cart.removeItem(item);
                    }
                }
            }
            if (deliveryInformation != null) {
                deliveryInformation.setType("SHIPPING");
                deliveryInformation.setPickupLocation(null);
            }
        } else {
            if (cart != null) {
                for (Item item : cart.getItems()) {
                    if ("EVENT".equals(item.getType())) {
                        Store currentStore = (Store)webSession.get("STORE");
                        if (storeToSwitchTo.hasItem(item)) {
                            cart.removeItem(item);
                            cart.addItem(storeToSwitchTo.getItem(item.getName()));
                        } else {
                            ((StoreEvent)item).setBillingData(storeToSwitchTo);
                            ((StoreEvent)item).setLocation(currentStore);
                        }
                    } else if (!storeToSwitchTo.hasItem(item)) {
                        cart.removeItem(item);
                    }
                }
            }
            Store currentStore = (Store)webSession.get("STORE");
            if (deliveryInformation.getType() != null
                    && "HOME_DELIVERY".equals(deliveryInformation.getType())
                    && deliveryInformation.getDeliveryAddress() != null) {
                if (!((LocationService)webSession.get("LOCATION_SERVICE")).isWithinDeliveryRange(storeToSwitchTo, deliveryInformation.getDeliveryAddress())) {
                    deliveryInformation.setType("PICKUP");
                    deliveryInformation.setPickupLocation(storeToSwitchTo);
                } else {
                    deliveryInformation.setPickupLocation(currentStore);
                }
            } else {
                if (((LocationService)webSession.get("LOCATION_SERVICE")).isWithinDeliveryRange(storeToSwitchTo, deliveryInformation.getDeliveryAddress())) {
                    deliveryInformation.setType("HOME_DELIVERY");
                    deliveryInformation.setPickupLocation(currentStore);
                    long weight = 0;
                    for (Item item: cart.getItems()) {
                        weight += item.getWeight();
                    }
                    deliveryInformation.setTotalWeight(weight);
                }
            }
        }
        webSession.put("STORE", storeToSwitchTo);
    }
}
