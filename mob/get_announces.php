<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 17/06/17
 * Time: 02:03 م
 */

$response=array();

if (isset($_GET["wilaya"]))
{
    $wilaya = $_GET["wilaya"];

    require "announceModel.php";
    require "Registry.php";

    $db = Registry::getInstance()->getDbConnection();
    $announceModel = new announceModel($db);

    if(($announces = $announceModel->getAnnounces($wilaya))!=null){
        $response["success"]=1;
        $response["message"]="retrieved succesufully";
        $response["announces"] = $announces;
    }else
    {
        $response["success"]=0;
        $response["message"]="error, retrieving failed";
    }
    echo json_encode($response);
}