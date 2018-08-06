package codingdojo;

/**
 * This class contains the information about how the customer would like to
 * have the contents of their shopping cart delivered to them.
 */
public class DeliveryInformation implements ModelObject {
    private String type;
    private String deliveryAddress;
    private Store pickupLocation;
    private long weight;

    public DeliveryInformation(String type, Store pickupLocation,
                               long weight) {
        this.type = type;
        this.pickupLocation = pickupLocation;
        this.weight = weight;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setPickupLocation(Store store) {
        this.pickupLocation = store;
    }

    public void setTotalWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "DeliveryInformation{" + "\n" +
                "type='" + type + '\'' + "\n" +
                "deliveryAddress='" + deliveryAddress + '\'' + "\n" +
                "pickupLocation=" + pickupLocation + "\n" +
                "weight=" + weight + "\n" +
                '}';
    }

    @Override
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }


    void switchStore(Store storeToSwitchTo, Cart cart, Store currentStore, LocationService location_service) {
        if (storeToSwitchTo == null) {
            changeToShipping();
        } else {
            if (cart != null) {
                long weight = cart.getWeight();
                boolean isWithinDeliveryRange = location_service.isWithinDeliveryRange(storeToSwitchTo, getDeliveryAddress());
                boolean hasDeliveryAddress = getDeliveryAddress() != null;
                boolean isHomeDelivery = getType() != null && "HOME_DELIVERY".equals(getType());

                if (hasDeliveryAddress) {
                    if (isWithinDeliveryRange) {
                        if (storeToSwitchTo.hasDroneDelivery() && weight < 500) {
                            changeToDroneDelivery(storeToSwitchTo, weight);
                        } else {
                            changeToHomeDelivery(storeToSwitchTo, weight);
                        }
                    } else {
                        if (isHomeDelivery) {
                            changeToPickup(currentStore);
                        }
                    }
                }
            }
        }
    }

    private void changeToShipping() {
        setType("SHIPPING");
        setPickupLocation(null);
    }

    private void changeToHomeDelivery(Store storeToSwitchTo, long weight) {
        setType("HOME_DELIVERY");
        setTotalWeight(weight);
        setPickupLocation(storeToSwitchTo);
    }

    private void changeToDroneDelivery(Store storeToSwitchTo, long weight) {
        setType("DRONE_DELIVERY");
        setTotalWeight(weight);
        setPickupLocation(storeToSwitchTo);
    }

    private void changeToPickup(Store currentStore) {
        setType("PICKUP");
        setPickupLocation(currentStore);
    }

}
