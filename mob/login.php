<?php
/**
 * Created by PhpStorm.
 * User: s
 * Date: 16/06/17
 * Time: 05:36 م
 */


$response=array();

if(isset($_POST["email"]) && isset($_POST["password"])) {
    $email = $_POST["email"];
    $password = $_POST["password"];

    require "Registry.php";
    require "loginModel.php";

    $db = Registry::getInstance()->getDbConnection();
    $loginModel = new loginModel($db);

    if (($result=$loginModel->getUser($email, $password)) != null) {
        $response["success"]=1;
        $response["message"]= "login successfully";
        
        $response["user"] = array();
        array_push($response["user"],$result);
    } else {
        $response["success"]=0;
        $response["message"]= "login failed";
    }
    echo json_encode($response);
}