package codingdojo;

import org.junit.Before;
import org.junit.Test;

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
        session.put("STORE", currentStore);;
        newStore = new Store("Backaplan", false);

        Item cherryBloom = new Item("Cherry Bloom", "LIPSTICK", 30);
        Item rosePetal = new Item("Rose Petal", "LIPSTICK", 30);
        Item blusherBrush =  new Item("Blusher Brush", "TOOL", 50);
        Item eyelashCurler = new Item("Eyelash curler", "TOOL", 100);

        currentStore.addStockedItems(cherryBloom, rosePetal, blusherBrush, eyelashCurler);
        newStore.addStockedItems(cherryBloom, rosePetal, blusherBrush, eyelashCurler);

        // Store events add themselves to the stocked items at their store
        Item masterclass = new StoreEvent("Eyeshadow Masterclass", currentStore);
        Item makeoverNordstan = new StoreEvent("Makeover", currentStore);
        Item makeoverBackaplan = new StoreEvent("Makeover", newStore);

        cart = (Cart)session.get("CART");
        cart.addItem(cherryBloom);
        cart.addItem(blusherBrush);
        cart.addItem(masterclass);
        cart.addItem(makeoverNordstan);

        deliveryInfo = new DeliveryInformation("PICKUP", currentStore, 60);
        session.put("DELIVERY_INFO", deliveryInfo);
    }

    @Test
    public void constructSession() {
        OnlineShopping shopping = new OnlineShopping(session);
        Approvals.verify(shopping);
    }


}
