<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 19/06/17
 * Time: 07:53 م
 */


$response=array();

if(isset($_POST["idAnnounce"]) && isset($_POST["idSender"]) && isset($_POST["idReciever"]) && isset($_POST["comment"]))
{

    require "commentModel.php";
    require "Registry.php";

    $idAnnounce = $_POST["idAnnounce"];
    $idSender = $_POST["idSender"];
    $idReciever =$_POST["idReciever"];
    $comment =  $_POST["comment"];

    $db = Registry::getInstance()->getDbConnection();
    $commentModel = new commentModel($db);
    $result=$commentModel->addComment($idAnnounce,$idSender,$idReciever,$comment);
    if($result){
        $response["success"]=1;
        $response["message"]="Commented successfully..";
    }else{
        $response["success"]=0;
        $response["message"]="Commenting failed..";
    }
    echo json_encode($response);
}
