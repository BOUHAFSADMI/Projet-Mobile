<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 19/06/17
 * Time: 08:56 م
 */



$response = array();

if(isset($_GET["idAnnounce"])){

    $idAnnounce = $_GET["idAnnounce"];

    require "Registry.php";
    require "commentModel.php";

    $db = Registry::getInstance()->getDbConnection();
    $commentModel = new commentModel($db);
    if(($comments = $commentModel->getComments($idAnnounce))!=null){
        $response["success"]=1;
        $response["message"]="retrieved successfully..";
        $response["comments"]=array();
        $response["comments"]=$comments;
    }else{
        $response["success"]=0;
        $response["message"]="retrieving failed..";
    }
    echo json_encode($response);
}