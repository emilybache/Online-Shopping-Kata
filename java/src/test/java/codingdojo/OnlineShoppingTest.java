package codingdojo;

import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.combinations.SkipCombination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OnlineShoppingTest {

    private Store backaplan;
    private Store nordstan;

    private Item cherryBloom;
    private Item rosePetal;
    private Item blusherBrush;
    private Item eyelashCurler;
    private Item wildRose;
    private Item cocoaButter;
    private Item masterclass;
    private Item makeoverNordstan;
    private Item makeoverBackaplan;

    @BeforeEach
    public void setUpReadOnlyObjects() {
        nordstan = new Store("Nordstan", false);
        backaplan = new Store("Backaplan", true);

        cherryBloom = new Item("Cherry Bloom", "LIPSTICK", 30);
        rosePetal = new Item("Rose Petal", "LIPSTICK", 30);
        blusherBrush = new Item("Blusher Brush", "TOOL", 50);
        eyelashCurler = new Item("Eyelash curler", "TOOL", 100);
        wildRose = new Item("Wild Rose", "PURFUME", 200);
        cocoaButter = new Item("Cocoa Butter", "SKIN_CREAM", 250);

        nordstan.addStockedItems(cherryBloom, rosePetal, blusherBrush, eyelashCurler, wildRose, cocoaButter);
        backaplan.addStockedItems(cherryBloom, rosePetal, eyelashCurler, wildRose, cocoaButter);

        // Store events add themselves to the stocked items at their store
        masterclass = new StoreEvent("Eyeshadow Masterclass", nordstan);
        makeoverNordstan = new StoreEvent("Makeover", nordstan);
        makeoverBackaplan = new StoreEvent("Makeover", backaplan);


    }

    @Test
    public void switchStore() throws Exception {

        CombinationApprovals.verifyAllCombinations(this::doSwitchStore,
                new String[]{"HOME_DELIVERY", "PICKUP", "SHIPPING", null},
                new String[]{"NEARBY", "NOT_NEARBY", null},
                new Store[]{backaplan, null},
                new Boolean[]{true, false},
                new Boolean[]{true, false});

    }

    public Object doSwitchStore(String deliveryType, String deliveryAddress, Store storeToSwitchTo,
                                boolean nullCart, boolean nullDeliveryInfo) {

        DeliveryInformation deliveryInfo = new DeliveryInformation(deliveryType, nordstan, 60);
        deliveryInfo.setDeliveryAddress(deliveryAddress);
        if (nullDeliveryInfo) {
            deliveryInfo = null;
            // if deliveryInfo is null then address is meaningless so skip this combination in the tests
            if (deliveryAddress != null) {
                throw new SkipCombination();
            }
        }

        Cart cart = new Cart();
        cart.addItem(cherryBloom);
        cart.addItem(blusherBrush);
        cart.addItem(masterclass);
        cart.addItem(makeoverNordstan);
        if (nullCart) {
            cart = null;
            if (deliveryInfo != null) {
                throw new SkipCombination();
            }
        }

        Session session = new NonSavingSession();
        session.put("STORE", nordstan);
        session.put("DELIVERY_INFO", deliveryInfo);
        session.put("CART", cart);
        OnlineShopping shopping = new OnlineShopping(session);

        shopping.switchStore(storeToSwitchTo);
        return shopping.toString();
    }


}
