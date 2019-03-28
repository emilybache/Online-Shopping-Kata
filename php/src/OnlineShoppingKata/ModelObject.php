<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 16.24
 */

namespace App\OnlineShoppingKata;


interface ModelObject
{
    public function __toString();

    public function saveToDatabase();
}