<?php

namespace App\OnlineShoppingKata;

/**
 * Class StoreEvent
 * @package OnlineShoppingKata
 *
 * Ticket to In-store event, eg makeover, eyeshadow masterclass
 * or beauty product launch evening reception
 */
class StoreEvent extends Item
{
    /**
     * @var Store
     */
    protected $location;

    /**
     * StoreEvent constructor.
     * @param string $name
     * @param Store $location
     */
    public function __construct($name, Store $location)
    {
        parent::__construct($name, "EVENT", 0);
        $this->setLocation($location);
    }

    /**
     * @param Store $locationStore
     */
    public function setLocation(Store $locationStore)
    {
        $this->location = $locationStore;
        $this->location->addStoreEvent($this);
    }

    /**
     * @return string
     */
    public function __toString() {
        return "StoreEvent{" .
            "name='" . $this->name . '\'' .
            ", location=" . $this->location .
            '}';
    }
}