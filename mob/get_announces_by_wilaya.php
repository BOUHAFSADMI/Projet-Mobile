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

$wilayas = $announceModel->getWilayas();


echo json_encode($announceModel->getAnnounceWilaya($wilayas));
