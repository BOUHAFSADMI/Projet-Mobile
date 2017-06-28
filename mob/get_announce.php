<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 17/06/17
 * Time: 02:03 م
 */

$response=array();

if (isset($_GET["id"]))
{
    $id = $_GET["id"];

    require "announceModel.php";
    require "Registry.php";

    $db = Registry::getInstance()->getDbConnection();
    $announceModel = new announceModel($db);

    if(($announce = $announceModel->getAnnounce($id))!=null){
        $response["success"]=1;
        $response["message"]="Announces retrieved succesufully";
        $response["announce"]=array();
        array_push($response["announce"],$announce);
    }else
    {
        $response["success"]=0;
        $response["message"]="error, retrieving failed";
    }
    echo json_encode($response);
}