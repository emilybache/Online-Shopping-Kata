<?php
/**
 * Created by PhpStorm.
 * User: luca
 * Date: 28/03/19
 * Time: 16.24
 */

namespace OnlineShoppingKata;


interface ModelObject
{
    public function toString();

    public function saveToDatabase();
}