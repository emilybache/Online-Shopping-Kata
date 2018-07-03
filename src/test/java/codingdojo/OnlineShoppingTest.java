package codingdojo;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        session = new Session();
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
        deliveryInfo = new DeliveryInformation("PICKUP", nordstan, 60);
        deliveryInfo.setDeliveryAddress(null);
        session.put("DELIVERY_INFO", deliveryInfo);
        // TODO: test something here
    }


}
