<?php

/**
 * Created by PhpStorm.
 * User: s
 * Date: 19/06/17
 * Time: 12:16 م
 */
class commentModel
{
    public function __construct(PDO $dbConnection)
    {
        $this->dbConnection = $dbConnection;
    }


    public function addComment($idAnnounce, $idSender, $idReciever, $comment)
    {
        $query = "INSERT  INTO comment(idAnnounce,idSender,idReciever,comment) VALUES ('".$idAnnounce."','".$idSender."','".$idReciever."','".$comment."')";
        $result=$this->dbConnection->query($query);
        return $result;
    }


    public function getComments($idAnnounce)
    {
        $query = "select * from comment where idAnnounce='".$idAnnounce."'";
        $result = $this->dbConnection->query($query);
        if (!empty($result)) {
            $comments = array();
            while (($row = $result->fetch()) != null) {
                $comment["id"] = $row["id"];
                $comment["idAnnounce"] = $row["idAnnounce"];
                $comment["idSender"] = $row["idSender"];
                $comment["idReciever"] = $row["idReciever"];
                $comment["comment"] = $row["comment"];

                array_push($comments, $comment);
            }
            return $comments;
        } else
            return null;
    }



}

