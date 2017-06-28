package com.sadmi.project.model;

import java.util.Date;

/**
 * Created by s on 23/04/17.
 */

public class Meeting {

    private int id;
    private int announce;
    private String sender;
    private String reciever;
    private String date;
    private String hour;
    private String status;


    public Meeting(int id,int announce, String sender, String reciever, String date, String hour,String status) {
        this.id=id;
        this.announce = announce;
        this.sender = sender;
        this.reciever = reciever;
        this.date = date;
        this.hour = hour;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Meeting(String sender, String date) {
        this.sender = sender;
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public int getAnnounce() {
        return announce;
    }

    public void setAnnounce(int announce) {
        this.announce = announce;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
