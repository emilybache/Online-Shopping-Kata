<?php

namespace App\OnlineShoppingKata;


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

    /**
     * DeliveryInformation constructor.
     * @param string $type
     * @param Store $pickupLocation
     * @param string $weight
     */
    public function __construct($type, Store $pickupLocation, $weight)
    {
        $this->type = $type;
        $this->pickupLocation = $pickupLocation;
        $this->weight = $weight;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    /**
     * @return string
     */
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

    /**
     * @param ?Store $store
     */
    public function setPickupLocation(?Store $store)
    {
        $this->pickupLocation = $store;
    }

    public function setTotalWeight($weight)
    {
        $this->weight = $weight;
    }

    /**
     * @return string
     */
    public function __toString() {
        return "DeliveryInformation{" . "\n" .
            "type='" . $this->type . '\'' . "\n" .
            "deliveryAddress='" . $this->deliveryAddress . '\'' . "\n" .
            "pickupLocation=" . $this->pickupLocation . "\n" .
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