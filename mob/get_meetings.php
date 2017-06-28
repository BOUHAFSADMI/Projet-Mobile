<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 19/06/17
 * Time: 09:08 م
 */

$response = array();

if(isset($_GET["idReciever"])){

    $idReciever = $_GET["idReciever"];

    require "Registry.php";
    require "meetingModel.php";

    $db = Registry::getInstance()->getDbConnection();
    $meetingModel = new meetingModel($db);

    if(($meeting = $meetingModel->getMeetings($idReciever))!=null){
        $response["success"]=1;
        $response["message"]="retrieved successfully..";
        $response["meetings"]=array();
        $response["meetings"]=$meeting;
    }else{
        $response["success"]=0;
        $response["message"]="retrieving failed..";
    }
    echo json_encode($response);
}