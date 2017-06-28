<?php

/**
 * Created by PhpStorm.
 * User: s
 * Date: 28/05/17
 * Time: 06:03 م
 */
class Registry
{
    private $dbConnection;
    public static $registry;

    public function __construct()
    {
        try {
            $pdo_option[PDO::ATTR_ERRMODE]=PDO::ERRMODE_EXCEPTION;
            $this->dbConnection= new PDO('mysql:host=localhost;dbname=mob_db', 'root', '');
        }
        catch(Exception $ex){
            Die("connection to DataBase failed");
        }
    }

    public static function getInstance(){
        if(Registry::$registry==null)
            $registry=new Registry();
        return $registry;
    }

    public function getDbConnection()
    {
        return $this->dbConnection;
    }
}
