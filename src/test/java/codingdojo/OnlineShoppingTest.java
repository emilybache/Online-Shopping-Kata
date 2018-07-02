package codingdojo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import org.approvaltests.Approvals;

public class OnlineShoppingTest {

    private Session session;
    private List<Item> items;
    private DeliveryInformation deliveryInfo;
    private Cart cart;
    private Store backaplan;
    private Store nordstan;

    // this is the system under test
    private OnlineShopping shopping;

    @Before
    public void setUp() {
        session = new NonSavingSession();
        nordstan = new Store("Nordstan", false);
        session.put("STORE", nordstan);
        backaplan = new Store("Backaplan", false);

        Item cherryBloom = new Item("Cherry Bloom", "LIPSTICK", 30);
        Item rosePetal = new Item("Rose Petal", "LIPSTICK", 30);
        Item blusherBrush =  new Item("Blusher Brush", "TOOL", 50);
        Item eyelashCurler = new Item("Eyelash curler", "TOOL", 100);

        nordstan.addStockedItems(cherryBloom, rosePetal, blusherBrush, eyelashCurler);
        backaplan.addStockedItems(cherryBloom, rosePetal, eyelashCurler);

        // Store events add themselves to the stocked items at their store
        Item masterclass = new StoreEvent("Eyeshadow Masterclass", nordstan);
        Item makeoverNordstan = new StoreEvent("Makeover", nordstan);
        Item makeoverBackaplan = new StoreEvent("Makeover", backaplan);

        cart = (Cart)session.get("CART");
        cart.addItem(cherryBloom);
        cart.addItem(blusherBrush);
        cart.addItem(masterclass);
        cart.addItem(makeoverNordstan);

        shopping = new OnlineShopping(session);
    }

    @Test
    public void constructSession() {
        setupDeliveryInformation("PICKUP", null);
        Approvals.verify(shopping);
    }
    @Test
    public void changeToWarehouse() {
        setupDeliveryInformation("PICKUP", null);
        shopping.switchStore(null);
        Approvals.verify(shopping);
    }
    @Test
    public void keepPickup() {
        setupDeliveryInformation("PICKUP", "NOT_NEARBY");
        shopping.switchStore(backaplan);
        Approvals.verify(shopping);
    }
    @Test
    public void changeFromPickupToHomeDelivery() {
        setupDeliveryInformation("PICKUP", "NEARBY");
        shopping.switchStore(backaplan);
        Approvals.verify(shopping);
    }
    @Test
    public void keepHomeDelivery() {
        setupDeliveryInformation("HOME_DELIVERY", "NEARBY");
        shopping.switchStore(backaplan);
        Approvals.verify(shopping);
    }
    @Test
    public void changeFromHomeDeliveryToPickup() {
        setupDeliveryInformation("HOME_DELIVERY", "NOT_NEARBY");
        shopping.switchStore(backaplan);
        Approvals.verify(shopping);
    }
    @Ignore("drone delivery not yet implemented")
    @Test
    public void keepDrone() {
        setupDeliveryInformation("DRONE", "NEARBY");
        nordstan.setDroneDelivery(true);
        backaplan.setDroneDelivery(true);
        shopping.switchStore(backaplan);
        Approvals.verify(shopping);
    }
    @Ignore("drone delivery not yet implemented")
    @Test
    public void changeFromDroneToPickup() {
        setupDeliveryInformation("DRONE", "NOT_NEARBY");
        nordstan.setDroneDelivery(true);
        backaplan.setDroneDelivery(false);
        shopping.switchStore(backaplan);
        Approvals.verify(shopping);
    }
    @Ignore("drone delivery not yet implemented")
    @Test
    public void changeFromHomeDeliveryToDrone() {
        setupDeliveryInformation("HOME_DELIVERY", "NEARBY");
        nordstan.setDroneDelivery(false);
        backaplan.setDroneDelivery(true);
        shopping.switchStore(backaplan);
        Approvals.verify(shopping);
    }

    private void setupDeliveryInformation(String currentDeliveryType, String deliveryAddress) {
        deliveryInfo = new DeliveryInformation(currentDeliveryType, nordstan, 60);
        deliveryInfo.setDeliveryAddress(deliveryAddress);
        session.put("DELIVERY_INFO", deliveryInfo);
    }


}
