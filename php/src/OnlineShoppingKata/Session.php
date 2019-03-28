<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 16.47
 */

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

    public function __construct() {
        $this->session = [];
        $this->session["CART"] = new Cart();
        $this->session["LOCATION_SERVICE"] = new LocationService();
    }

    public function get($key) {
        return $this->session[$key];
    }

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

    public function __toString() {
        $sessionContents = "\n";
        foreach ($this->session as $key => $value) {
            /** @var ModelObject $value */
            $sessionContents .= $key . "=" . $value . "\n";
        }

        return "Session{" . $sessionContents . "}";
    }
}