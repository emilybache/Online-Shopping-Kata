<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 16.55
 */

namespace App\OnlineShoppingKata;

/**
 * Class Store
 * @package OnlineShoppingKata
 *
 * Represents a physical Store where you can go and buy
 * products and attend events.
 */
class Store implements ModelObject
{
    private $itemsInStock = [];
    /**
     * @var string
     */
    private $name;

    /**
     * @var bool
     */
    private $droneDelivery;

    public function __construct($name, $droneDelivery)
    {
        $this->name = $name;
        $this->droneDelivery = $droneDelivery;
    }

    public function addStockedItems($items)
    {
        foreach ($items as $item) {
            /** @var Item $item */
            $this->itemsInStock['name'] = $item;
        }
    }

    public function addStoreEvent(StoreEvent $storeEvent)
    {
        $this->itemsInStock[$storeEvent->getName()] = $storeEvent;
    }

    public function removeStockedItems($items)
    {
        foreach ($items as $item) {
            /** @var Item $item */
            unset($this->itemsInStock[$item->getName()]);
        }
    }

    public function hasItem(Item $item)
    {
        return key_exists($item->getName(), $this->itemsInStock);
    }

    public function getItem($name)
    {
        return $this->itemsInStock[$name];
    }

    public function hasDroneDelivery() {
        return $this->droneDelivery;
    }

    public function __toString()
    {
        return "Store{" .
            "name='" . $this->name . "\', " .
            "droneDelivery=" . $this->droneDelivery .
            '}';
    }

    /**
     * @throws UnsupportedOperationException
     */
    public function saveToDatabase()
    {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}