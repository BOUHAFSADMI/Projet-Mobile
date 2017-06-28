<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 17/06/17
 * Time: 02:03 م
 */

$response=array();


    require "announceModel.php";
    require "Registry.php";

    $db = Registry::getInstance()->getDbConnection();
    $announceModel = new announceModel($db);

    $type = $announceModel->getType();


echo json_encode($announceModel->getAnnounceType($type));
