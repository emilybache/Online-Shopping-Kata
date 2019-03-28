<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 17.23
 */

namespace OnlineShoppingKata;


class DeliveryInformation implements ModelObject
{
    /**
     * @var string
     */
    private $type;

    /**
     * @var string
     */
    private $deliveryAddress;

    /**
     * @var Store
     */
    private $pickupLocation;

    /**
     * @var float
     */
    private $weight;

    public function __construct($type, Store $pickupLocation, $weight)
    {
        $this->type = $type;
        $this->pickupLocation = $pickupLocation;
        $this->weight = $weight;
    }

    public function setType($type)
    {
        $this->type = $type;
    }

    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $deliveryAddress
     */
    public function setDeliveryAddress($deliveryAddress)
    {
        $this->deliveryAddress = $deliveryAddress;
    }

    /**
     * @return string
     */
    public function getDeliveryAddress()
    {
        return $this->deliveryAddress;
    }

    public function setPickupLocation(Store $store)
    {
        $this->pickupLocation = $store;
    }

    public function setTotalWeight($weight)
    {
        $this->weight = $weight;
    }

    public function toString() {
        return "DeliveryInformation{" . "\n" .
            "type='" . $this->type . '\'' . "\n" .
            "deliveryAddress='" . $this->deliveryAddress . '\'' . "\n" .
            "pickupLocation=" . $this->pickupLocation->toString() . "\n" .
            "weight=" . $this->weight . "\n" .
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