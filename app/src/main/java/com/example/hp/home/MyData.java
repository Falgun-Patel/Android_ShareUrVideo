package com.example.hp.home;


/**
 * Created by filipp on 9/16/2016.
 */
public class MyData {

    private int id;
    private String description,image_link,like,idd;

    public MyData(int id, String image_link, String description,String like,String idd) {
        this.id = id;
        this.image_link = image_link;
        this.description = description;
        this.like= like;
        this.idd=idd;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getidd() {
        return idd;
    }

    public void setidd(String idd) {
        this.idd = idd;
    }

    public String getlike() {
        return like;
    }

    public void setlike(String like) {
        this.like = like;
    }

}