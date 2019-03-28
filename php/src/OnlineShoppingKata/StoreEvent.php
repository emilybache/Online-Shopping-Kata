<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 17.16
 */

namespace OnlineShoppingKata;

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

    public function __construct($name, Store $location)
    {
        parent::__construct($name, "EVENT", 0);
        $this->setLocation($location);
    }

    public function setLocation(Store $locationStore)
    {
        $this->location = $locationStore;
        $this->location->addStoreEvent($this);
    }

    public function toString() {
        return "StoreEvent{" .
            "name='" . $this->name . '\'' .
            ", location=" . $this->location->toString() .
            '}';
    }
}