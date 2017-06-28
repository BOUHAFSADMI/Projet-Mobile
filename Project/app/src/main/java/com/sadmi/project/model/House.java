package com.sadmi.project.model;

import java.io.Serializable;

/**
 * Created by s on 26/03/17.
 */

public class House implements Serializable {

    private int id;
    private String surface;
    private String address;
    private Wilaya wilaya;
    private HouseType type;
    private String price;
    private String image;
    private String image1;
    private String image2;
    private String image3;
    private Double lat;
    private Double lng;
    private String userid;


    public House(int id, String surface, String address, Wilaya wilaya, HouseType type, String price, String image, Double lat, Double lng) {
        this.id = id;
        this.surface = surface;
        this.address = address;
        this.wilaya = wilaya;
        this.type = type;
        this.price = price;
        this.image = image;
        this.lat = lat;
        this.lng = lng;
    }


    public House(int id, String address, String price, String image,HouseType type) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.image = image;
        this.type = type;
    }

    public House(int id, String surface, String address, Wilaya wilaya, HouseType type, String price, String image, String image1, String image2, String image3, Double lat, Double lng, String userid) {
        this.id = id;
        this.surface = surface;
        this.address = address;
        this.wilaya = wilaya;
        this.type = type;
        this.price = price;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.lat = lat;
        this.lng = lng;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Wilaya getWilaya() {
        return wilaya;
    }

    public void setWilaya(Wilaya wilaya) {
        this.wilaya = wilaya;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public HouseType getType() {
        return type;
    }

    public void setType(HouseType type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}