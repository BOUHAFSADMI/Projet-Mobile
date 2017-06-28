<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 20/06/17
 * Time: 09:48 ص
 */

$response=array();

if(isset($_POST["id"]) && isset($_POST["status"]))
{
    $id=$_POST["id"];
    $status=$_POST["status"];

    require "Registry.php";
    require "meetingModel.php";

    $db = Registry::getInstance()->getDbConnection();
    $meetingModel = new meetingModel($db);
    $result = $meetingModel->updateMeeting($id, $status);
    if($result){
        $response["success"]=1;
        $response["message"]="updated successfully..";
    }else{
        $response["success"]=0;
        $response["message"]="update failed..";
    }
    echo json_encode($response);
}