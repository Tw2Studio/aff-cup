package com.tw2.affsuzukicup.model;

public class ItemHomePager {

    private String name;
    private String image;
    private String link;


    public ItemHomePager(String name, String image, String link) {
        this.name = name;
        this.image = image;
        this.link = link;
    }

    public ItemHomePager() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
