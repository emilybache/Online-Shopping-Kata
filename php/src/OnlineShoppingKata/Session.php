<?php

namespace App\OnlineShoppingKata;

/**
 * Class Session
 * @package OnlineShoppingKata
 *
 * Allows the OnlineShopping to access data classes
 * and store them in the database
 */
class Session
{
    private $session;

    /**
     * Session constructor.
     */
    public function __construct() {
        $this->session = [];
        $this->session["CART"] = new Cart();
        $this->session["LOCATION_SERVICE"] = new LocationService();
    }

    /**
     * @param string $key
     * @return ModelObject
     */
    public function get($key) {
        return $this->session[$key];
    }

    /**
     * @param $key
     * @param ModelObject $value
     */
    public function put($key, ModelObject $value) {
        $this->session[$key] = $value;
    }

    public function saveAll() {
        foreach (array_keys($this->session) as $array_key) {
            /** @var ModelObject $entity */
            $entity = $this->session[$array_key];
            $entity->saveToDatabase();
        }
    }

    /**
     * @return string
     */
    public function __toString() {
        $sessionContents = "\n";
        foreach ($this->session as $key => $value) {
            /** @var ModelObject $value */
            $sessionContents .= $key . "=" . $value . "\n";
        }

        return "Session{" . $sessionContents . "}";
    }
}