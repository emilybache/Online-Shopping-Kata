package codingdojo;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class OnlineShoppingTest {

    private WebSession session;
    private List<Item> items;
    private DeliveryInformation deliveryInfo;
    private Cart cart;
    private Store newStore;

    @Before
    public void setUp() {
        session = new WebSession();
        Store currentStore = new Store("Nordstan", false);
        session.setCurrentStore(currentStore);
        newStore = new Store("Backaplan", false);
        items = Arrays.asList(
                new Item("Cherry Bloom", "LIPSTICK", 30),
                new Item("Rose Petal", "LIPSTICK", 30),
                new Item("Blusher Brush", "TOOL", 50),
                new Item("Eyelash curler", "TOOL", 100),
                new StoreEvent("Eyeshadow Masterclass", currentStore),
                new StoreEvent("Makeover", currentStore),
                new StoreEvent("Makeover", newStore)
        );
        cart = session.getCart();
        cart.addItems(items);
        deliveryInfo = new DeliveryInformation("PICKUP", currentStore, 300);
        session.setDeliveryInfo(deliveryInfo);
    }

    @Test
    public void testSwitchStoreEmptySession() {
        session.clear();
        new OnlineShopping(session).switchStore(null);
    }

    @Test
    public void switchToWarehouse() {
        new OnlineShopping(session).switchStore(null);
    }

    @Test
    public void noDeliveryInfo() {
        session.setDeliveryInfo(null);
        new OnlineShopping(session).switchStore(newStore);
    }

    @Test
    public void switchToNewStorePickupToDelivery() {
        deliveryInfo.setType("PICKUP");
        deliveryInfo.setDeliveryAddress("NEARBY");
        new OnlineShopping(session).switchStore(newStore);
    }

    @Test
    public void switchToNewStoreKeepDelivery() {
        deliveryInfo.setType("HOME_DELIVERY");
        deliveryInfo.setDeliveryAddress("NEARBY");
        new OnlineShopping(session).switchStore(newStore);
    }

    @Test
    public void switchToNewStoreChangeToPickup() {
        deliveryInfo.setType("HOME_DELIVERY");
        deliveryInfo.setDeliveryAddress("NOT_NEARBY");
        new OnlineShopping(session).switchStore(newStore);
    }
}
