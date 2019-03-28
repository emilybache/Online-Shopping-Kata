<?php

namespace Tests\OnlineShoppingKata;


use App\OnlineShoppingKata\Cart;
use App\OnlineShoppingKata\DeliveryInformation;
use App\OnlineShoppingKata\Item;
use App\OnlineShoppingKata\OnlineShopping;
use App\OnlineShoppingKata\Session;
use App\OnlineShoppingKata\Store;
use App\OnlineShoppingKata\StoreEvent;
use PHPUnit\Framework\TestCase;

class OnlineShoppingTest extends TestCase
{
    /**
     * Stores
     */
    private $backaplan;
    private $nordstan;

    /**
     * Items
     */
    private $cherryBloom;
    private $rosePetal;
    private $blusherBrush;
    private $eyelashCurler;
    private $wildRose;
    private $cocoaButter;
    private $masterclass;
    private $makeoverNordstan;
    private $makeoverBackaplan;

    protected function setUp() : void
    {
        $this->nordstan = new Store("Nordstan", false);
        $this->backaplan = new Store("Backaplan", true);

        $this->cherryBloom = new Item("Cherry Bloom", "LIPSTICK", 30);
        $this->rosePetal = new Item("Rose Petal", "LIPSTICK", 30);
        $this->blusherBrush = new Item("Blusher Brush", "TOOL", 50);
        $this->eyelashCurler = new Item("Eyelash curler", "TOOL", 100);
        $this->wildRose = new Item("Wild Rose", "PURFUME", 200);
        $this->cocoaButter = new Item("Cocoa Butter", "SKIN_CREAM", 250);

        $this->nordstan->addStockedItems([
            $this->cherryBloom,
            $this->rosePetal,
            $this->blusherBrush,
            $this->eyelashCurler,
            $this->wildRose,
            $this->cocoaButter
        ]);
        $this->backaplan->addStockedItems([
            $this->cherryBloom,
            $this->rosePetal,
            $this->eyelashCurler,
            $this->wildRose,
            $this->cocoaButter
        ]);

        // Store events add themselves to the stocked items at their store
        $this->masterclass = new StoreEvent("Eyeshadow Masterclass", $this->nordstan);
        $this->makeoverNordstan = new StoreEvent("Makeover", $this->nordstan);
        $this->makeoverBackaplan = new StoreEvent("Makeover", $this->backaplan);
    }

    public function testSwitchStore()
    {
        $deliveryInfo = new DeliveryInformation("HOME_DELIVERY", $this->nordstan, 60);
        $deliveryInfo->setDeliveryAddress("NEARBY");

        $cart = new Cart();
        $cart->addItem($this->cherryBloom);
        $cart->addItem($this->blusherBrush);
        $cart->addItem($this->masterclass);
        $cart->addItem($this->makeoverNordstan);

        $session = new Session();
        $session->put("STORE", $this->nordstan);
        $session->put("DELIVERY_INFO", $deliveryInfo);
        $session->put("CART", $cart);
        $shopping = new OnlineShopping($session);

        // TODO: make this test work
        // $shopping->switchStore($this->backaplan);
        // assertEquals("DRONE", session->get("DELIVERY_INFO").getType());
    }
}
