package codingdojo;

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
        return "DeliveryInformation{" +
                "type='" + type + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", pickupLocation=" + pickupLocation +
                ", weight=" + weight +
                '}';
    }
}
