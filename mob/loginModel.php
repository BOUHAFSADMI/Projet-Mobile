<?php

/**
 * Created by PhpStorm.
 * User: s
 * Date: 28/05/17
 * Time: 01:40 م
 */

class loginModel
{

    private $dbConnection;

    public function __construct(PDO $dbConnection)
    {
       $this->dbConnection = $dbConnection;
    }



    public function getUser($email,$password)
    {
        $query="SELECT * FROM user WHERE email='".$email."'AND password='".$password."'";
        $result = $this->dbConnection->query($query);
        $row = $result->fetch();
        if($row!=null){
            $user=array();
            $user["email"]=$row["email"];
            $user["password"]=$row["password"];
            return $user;
        }
        else
            return null;
    }


}