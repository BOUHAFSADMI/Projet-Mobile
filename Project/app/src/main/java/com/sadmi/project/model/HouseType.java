package com.sadmi.project.model;

/**
 * Created by s on 26/03/17.
 */

public enum HouseType {
    appartement ("appartement"),
    studio ("studio"),
    duplex ("duplex"),
    villa ("villa");

    private String type="";

    HouseType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
