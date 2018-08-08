package codingdojo;

/**
 * The LocationService can tell you if a delivery address is within delivery range
 * of a particular Store. This is a placeholder implementation.
 */
public class LocationService implements ModelObject {
    boolean isWithinDeliveryRange(Store store, String deliveryAddress) {
        return "NEARBY".equals(deliveryAddress);
    }

    @Override
    public String toString() {
        return "LocationService";
    }

    @Override
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

}
