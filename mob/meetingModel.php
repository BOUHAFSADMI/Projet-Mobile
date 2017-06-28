<?php

/**
 * Created by PhpStorm.
 * User: s
 * Date: 19/06/17
 * Time: 12:16 م
 */
class meetingModel
{
    public function __construct(PDO $dbConnection)
    {
        $this->dbConnection = $dbConnection;
    }


    public function addMeeting($idAnnounce, $idSender, $idReciever, $date,$time,$status)
    {
        $query = "INSERT  INTO meeting(idAnnounce,idSender,idReciever,date,time,status) VALUES ('" .$idAnnounce."','".$idSender."','".$idReciever ."','" . $date . "','".$time."','".$status."')";
        $result=$this->dbConnection->query($query);
        return $result;
    }


    public function getMeetings($idReciever)
    {
        $query = "select * from meeting where idReciever='".$idReciever."'";
        $result = $this->dbConnection->query($query);
        if (!empty($result)) {

            $meetings = array();
            while (($row = $result->fetch()) != null) {
                $meeting = array();
                $meeting["id"] = $row["id"];
                $meeting["idAnnounce"] = $row["idAnnounce"];
                $meeting["idSender"] = $row["idSender"];
                $meeting["idReciever"] = $row["idReciever"];
                $meeting["date"] = $row["date"];
                $meeting["time"] = $row["time"];
                $meeting["status"] = $row["status"];

                array_push($meetings, $meeting);
            }
            return $meetings;
        } else
            return null;
    }

    public function getMeetingsByStatus($idReciever,$status)
    {
        $query = "select * from meeting where idReciever='".$idReciever."'and status='".$status."'";
        $result = $this->dbConnection->query($query);
        if (!empty($result)) {

            $meetings = array();
            while (($row = $result->fetch()) != null) {
                $meeting = array();
                $meeting["id"] = $row["id"];
                $meeting["idAnnounce"] = $row["idAnnounce"];
                $meeting["idSender"] = $row["idSender"];
                $meeting["idReciever"] = $row["idReciever"];
                $meeting["date"] = $row["date"];
                $meeting["time"] = $row["time"];
                $meeting["status"] = $row["status"];

                array_push($meetings, $meeting);
            }
            return $meetings;
        } else
            return null;
    }

    public function updateMeeting($id,$status){
        $query = "UPDATE meeting SET status='" . $status . "' WHERE id='".$id."'";
        $result = $this->dbConnection->query($query);
        return $result;
    }
}


