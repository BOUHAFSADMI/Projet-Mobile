package com.sadmi.project.model;

/**
 * Created by s on 20/06/17.
 */

public class Comment {


    private int idAnnounce;
    private String idSender;
    private String idReciever;
    private String comment;


    public Comment(String idSender, String comment) {
        this.idSender = idSender;
        this.comment = comment;
    }

    public int getIdAnnounce() {
        return idAnnounce;
    }

    public void setIdAnnounce(int idAnnounce) {
        this.idAnnounce = idAnnounce;
    }

    public String getIdSender() {
        return idSender;
    }

    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }

    public String getIdReciever() {
        return idReciever;
    }

    public void setIdReciever(String idReciever) {
        this.idReciever = idReciever;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
