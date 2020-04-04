import Store from "./Store";
import Item from "./Item";
import StoreEvent from "./StoreEvent";
import DeliveryInformation from "./DeliveryInformation";
import Cart from "./Cart";
import OnlineShopping from "./OnlineShopping";
import Session from "./Session";

describe('OnlineShoppingTest', () => {
  let backaplan: Store;
  let nordstan: Store;

  let cherryBloom: Item;
  let rosePetal: Item;
  let blusherBrush: Item;
  let eyelashCurler: Item;
  let wildRose: Item;
  let cocoaButter: Item;
  let masterclass: Item;
  let makeoverNordstan: Item;
  let makeoverBackaplan: Item;

  beforeEach(() => {
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
  })


  it('switchStore', () => {
    const deliveryInfo = new DeliveryInformation("HOME_DELIVERY", nordstan, 60);
    deliveryInfo.setDeliveryAddress("NEARBY");

    const cart = new Cart();
    cart.addItem(cherryBloom);
    cart.addItem(blusherBrush);
    cart.addItem(masterclass);
    cart.addItem(makeoverNordstan);

    const session = new Session();
    session.put("STORE", nordstan);
    session.put("DELIVERY_INFO", deliveryInfo);
    session.put("CART", cart);
    const shopping = new OnlineShopping(session);

    // TODO: make this test work
    // shopping.switchStore(backaplan);
    // assertEquals("DRONE", ((DeliveryInformation)session.get("DELIVERY_INFO")).getType());
  })
})
