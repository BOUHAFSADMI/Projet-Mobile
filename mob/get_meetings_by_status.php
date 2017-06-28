<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 20/06/17
 * Time: 05:46 ص
 */


$response=array();


if(isset($_GET["idReciever"]) && isset($_GET["status"])){

    $idReciever=$_GET["idReciever"];
    $status=$_GET["status"];

    require "Registry.php";
    require "meetingModel.php";

    $db = Registry::getInstance()->getDbConnection();
    $meetingModel = new meetingModel($db);

    if(($meeting = $meetingModel->getMeetingsByStatus($idReciever, $status))!=null){
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