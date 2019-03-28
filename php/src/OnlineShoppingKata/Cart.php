<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 16.25
 */

namespace App\OnlineShoppingKata;

/**
 * Class Cart
 * @package OnlineShoppingKata
 *
 * While shopping online in a Store, the Cart stores the Items you intend to buy
 */
class Cart implements ModelObject
{
    private $items = [];
    private $unavailableItems = [];

    public function getItems()
    {
        return $this->items;
    }

    public function addItem(Item $item)
    {
        $this->items[] = $item;
    }

    public function addItems($items)
    {
        foreach ($items as $item) {
            $this->items[] = $item;
        }
    }

    public function markAsUnavailable(Item $item)
    {
        $this->unavailableItems[] = $item;
    }

    public function __toString()
    {
        return "Cart{" .
            "items=" . $this->displayItems($this->items) .
            "unavailable=" . $this->displayItems($this->unavailableItems) .
            '}';
    }

    private function displayItems($items)
    {
        $itemDisplay = "\n";
        foreach ($items as $item) {
            /** @var $item Item */
            $itemDisplay .= $item . "\n";
        }

        return $itemDisplay;
    }

    /**
     * @throws UnsupportedOperationException
     */
    public function saveToDatabase()
    {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }

    public function getUnavailableItems()
    {
        return $this->unavailableItems;
    }
}