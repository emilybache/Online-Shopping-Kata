package codingdojo;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import org.approvaltests.Approvals;

public class OnlineShoppingTest {

    private Session session;
    private List<Item> items;
    private DeliveryInformation deliveryInfo;
    private Cart cart;
    private Store newStore;

    @Before
    public void setUp() {
        session = new Session();
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
    public void emptySession() {
        session.clear();
        OnlineShopping shopping = new OnlineShopping(session);
        shopping.switchStore(null);
        Approvals.verify(shopping);
    }


}
