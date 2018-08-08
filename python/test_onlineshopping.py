
from approvaltests.combination_approvals import verify_all_combinations

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


class NonSavingSession(Session):
    def save_all(self):
        pass


def test_switch_store():
    """This test case uses approvaltests. See the documentation at https://github.com/approvals/ApprovalTests.Python"""
    verify_all_combinations(do_switch_store,
        [["HOME_DELIVERY", "PICKUP", "SHIPPING", None],
         ["NEARBY", "NOT_NEARBY", None],
         [backaplan, None],
         [True, False],
         [True, False]])


def do_switch_store(delivery_type, delivery_address, store_to_switch_to, no_cart, no_delivery_info):
    delivery_info = DeliveryInformation(delivery_type, store_to_switch_to, 60, delivery_address)
    if no_delivery_info:
        delivery_info = None

    cart = Cart()
    cart.add(cherryBloom)
    cart.add(blusherBrush)
    cart.add(masterclass)
    cart.add(makeoverNordstan)
    if no_cart:
        cart = None

    session = NonSavingSession()
    session.put("STORE", nordstan)
    session.put("DELIVERY_INFO", delivery_info)
    session.put("CART", cart)
    shopping = OnlineShopping(session)

    shopping.switch_store(backaplan)
    return repr(shopping)


