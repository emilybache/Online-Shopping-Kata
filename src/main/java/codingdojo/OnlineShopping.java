package codingdojo;

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
public class OnlineShopping {

    private Session session;

    public OnlineShopping(Session session) {
        this.session = session;
    }

    /**
     * This method is called when the user changes the
     * store they are shopping at in the online shopping
     * website.
     */
    public void switchStore(Store storeToSwitchTo) {
        Cart cart = (Cart) session.get("CART");
        DeliveryInformation deliveryInformation = (DeliveryInformation) session.get("DELIVERY_INFO");
        Store currentStore = (Store) session.get("STORE");
        LocationService location_service = (LocationService) session.get("LOCATION_SERVICE");

        if (cart != null) {
            cart.doSwitchStore(storeToSwitchTo);
        }
        if (deliveryInformation != null) {
            deliveryInformation.switchStore(storeToSwitchTo, cart, currentStore, location_service);
        }

        session.put("STORE", storeToSwitchTo);
        session.saveAll();
    }

    @Override
    public String toString() {
        return "OnlineShopping{\n"
                + "session=" + session + "\n}";
    }
}
