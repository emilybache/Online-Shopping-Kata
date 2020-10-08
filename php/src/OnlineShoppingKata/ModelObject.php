<?php

namespace App\OnlineShoppingKata;


interface ModelObject
{
    public function __toString();

    public function saveToDatabase();
}