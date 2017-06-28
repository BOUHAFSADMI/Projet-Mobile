<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 19/06/17
 * Time: 09:21 م
 */



$response=array();

if( isset($_POST["idAnnounce"]) && isset($_POST["idSender"]) && isset($_POST["idReciever"]) && isset($_POST["date"]) && isset($_POST["time"]) && isset($_POST["status"]))
{

    require "meetingModel.php";
    require "Registry.php";

    $idAnnounce =$_POST["idAnnounce"];
    $idSender =  $_POST["idSender"];
    $idReciever = $_POST["idReciever"];
    $date =$_POST["date"];
    $time =$_POST["time"];
    $status =$_POST["status"];

    $db = Registry::getInstance()->getDbConnection();
    $meetingModel = new meetingModel($db);

     $result=$meetingModel->addMeeting($idAnnounce,$idSender,$idReciever,$date,$time,$status);
     if($result){
         $response["success"]=1;
         $response["message"]="Commented successfully..";
     }else{
         $response["success"]=0;
         $response["message"]="Commenting failed..";
     }
     echo json_encode($response);
}
