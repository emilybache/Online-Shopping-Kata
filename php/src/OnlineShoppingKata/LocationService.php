<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 17.39
 */

namespace App\OnlineShoppingKata;

/**
 * Class LocationService
 * @package OnlineShoppingKata
 *
 * The LocationService can tell you if a delivery address is within delivery range
 * of a particular Store. This is a placeholder implementation.
 */
class LocationService
{
    /**
     * @param Store $store
     * @param string $deliveryAddress
     *
     * @return bool
     */
    protected function isWithinDeliveryRange(Store $store, $deliveryAddress)
    {
        return "NEARBY" === $deliveryAddress;
    }

    /**
     * @return string
     */
    public function __toString()
    {
        return "LocationService";
    }

    /**
     * @throws UnsupportedOperationException
     */
    public function saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}