<?php

/**
 * Created by PhpStorm.
 * User: s
 * Date: 28/05/17
 * Time: 01:58 م
 */



class announceModel
{

    public function __construct(PDO $dbConnection)
    {
        $this->dbConnection = $dbConnection;
    }


    public function  getAnnounces($wilaya){
        $query = "select * from announce where wilaya='".$wilaya."'";
        $result = $this->dbConnection->query($query);

        if($result!=null){
            $announces = array();
            while(($row=$result->fetch())!=null)
            {
                $announce=array();
                $announce["id"]=$row["id"];
                $announce["price"]=$row["price"];
                $announce["address"]=$row["address"];
                $announce["image"]=$row["image1"];
                $announce["type"]=$row["type"];
                $announce["surface"] = $row["surface"];
                $announce["wilaya"] = $row["wilaya"];
                $announce["image1"] = $row["image1"];
                $announce["image2"] = $row["image2"];
                $announce["image3"] = $row["image3"];
                $announce["image4"] = $row["image4"];
                $announce["userid"] = $row["userid"];
                $announce["lat"] = $row["lat"];
                $announce["lng"] = $row["lng"];

                array_push($announces,$announce);
            }
            return $announces;
        }else
            return null;

    }


    public function  getAnnounce($id){
        $query = "select userid, surface, image2, image3, image4 from announce WHERE id='".$id."'";
        $result = $this->dbConnection->query($query);
        $row = $result->fetch();
        if(!empty($row)){
            $announce=array();
            $announce["userid"]=$row["userid"];
            $announce["surface"]=$row["surface"];
            $announce["image2"]=$row["image2"];
            $announce["image3"]=$row["image3"];
            $announce["image4"]=$row["image4"];
            return $announce;
        }else
            return null;
    }


    public function  getType(){
        $query = "select distinct type from announce";
        $result = $this->dbConnection->query($query);

        if($result!=null){
            $types = array();
            while(($row=$result->fetch())!=null)
            {
                array_push($types,$row["type"]);
            }
            return $types;
        }else
            return null;

    }



    public function  getAnnounceType($types)
    {
        $query = "select * from announce";
        $result = $this->dbConnection->query($query);

        if(!empty($result)){
            $announces=array();
            foreach($types as $type){
                $announces[$type]=array();            }

            while(($row=$result->fetch())!=null)
            {
                $announce=array();
                $announce["id"]=$row["id"];
                $announce["userid"]=$row["userid"];
                $announce["price"]=$row["price"];
                $announce["surface"]=$row["surface"];
                $announce["address"]=$row["address"];
                $announce["type"]=$row["type"];
                $announce["image1"]=$row["image1"];
                $announce["image2"]=$row["image2"];
                $announce["image3"]=$row["image3"];
                $announce["image4"]=$row["image4"];
                array_push($announces[$row["type"]],$announce);
            }
            return $announces;
        }else
            return null;

    }



    public function  getWilayas(){
        $query = "select distinct wilaya from announce";
        $result = $this->dbConnection->query($query);

        if($result!=null){
            $wilayas = array();
            while(($row=$result->fetch())!=null)
            {
                array_push($wilayas, $row["wilaya"]);
            }
            return $wilayas;
        }else
            return null;

    }

    public function  getAnnounceWilaya($wilayas)
    {
        $query = "select * from announce";
        $result = $this->dbConnection->query($query);

        if(!empty($result)){
            $announces=array();
            foreach($wilayas as $wilaya){
                $announces[$wilaya]=array();            }

            while(($row=$result->fetch())!=null)
            {
                $announce=array();
                $announce["id"]=$row["id"];
                $announce["userid"]=$row["userid"];
                $announce["price"]=$row["price"];
                $announce["surface"]=$row["surface"];
                $announce["address"]=$row["address"];
                $announce["type"]=$row["type"];
                $announce["image1"]=$row["image1"];
                $announce["image2"]=$row["image2"];
                $announce["image3"]=$row["image3"];
                $announce["image4"]=$row["image4"];
                array_push($announces[$row["wilaya"]],$announce);
            }
            return $announces;
        }else
            return null;

    }


}