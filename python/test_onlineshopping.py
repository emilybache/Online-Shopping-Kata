
from modelobjects import *
from session import *
from onlineshopping import OnlineShopping

nordstan = Store("Nordstan", False)
backaplan = Store("Backaplan", True)

cherryBloom = Item("Cherry Bloom", "LIPSTICK", 30)
rosePetal = Item("Rose Petal", "LIPSTICK", 30)
blusherBrush = Item("Blusher Brush", "TOOL", 50)
eyelashCurler = Item("Eyelash curler", "TOOL", 100)
wildRose = Item("Wild Rose", "PURFUME", 200)
cocoaButter = Item("Cocoa Butter", "SKIN_CREAM", 250)

nordstan.add_stocked_items(cherryBloom, rosePetal, blusherBrush, eyelashCurler, wildRose, cocoaButter)
backaplan.add_stocked_items(cherryBloom, rosePetal, eyelashCurler, wildRose, cocoaButter)

# Store events add themselves to the stocked items at their store
masterclass = StoreEvent("Eyeshadow Masterclass", nordstan)
makeoverNordstan = StoreEvent("Makeover", nordstan)
makeoverBackaplan = StoreEvent("Makeover", backaplan)


def test_onlineshopping():
    """This test uses py.test - see http://pytest.org"""
    delivery_info = DeliveryInformation("HOME_DELIVERY", nordstan, 60, "NEARBY")

    cart = Cart()
    cart.add(cherryBloom)
    cart.add(blusherBrush)
    cart.add(masterclass)
    cart.add(makeoverNordstan)

    session = Session()
    session.put("STORE", nordstan)
    session.put("DELIVERY_INFO", delivery_info)
    session.put("CART", cart)
    shopping = OnlineShopping(session)

    # TODO: make this test work
    shopping.switch_store(backaplan)
    assert "DRONE" == session.get("DELIVERY_INFO").delivery_type
