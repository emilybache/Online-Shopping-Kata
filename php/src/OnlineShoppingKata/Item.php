<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 16.28
 */

namespace App\OnlineShoppingKata;

/**
 * Class Item
 * @package OnlineShoppingKata
 *
 * Items are for sale in a Store (or the central warehouse) and can be put in a Cart
 */
class Item
{
    /**
     * @var string
     */
    protected $name;

    /**
     * @var string
     */
    protected $type;

    /**
     * @var float
     */
    protected $weight; // in grams

    public function __construct($name, $type, $weight)
    {
        $this->name = $name;
        $this->type = $type;
        $this->weight = $weight;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return float
     */
    public function getWeight()
    {
        return $this->weight;
    }

    public function __toString()
    {
        return "Item{" .
            "name='" . $this->name . '\'' .
            ", type='" . $this->type . '\'' .
            ", weight=" . $this->weight .
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