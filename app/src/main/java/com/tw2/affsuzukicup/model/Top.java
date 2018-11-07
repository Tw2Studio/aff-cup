package com.tw2.affsuzukicup.model;

public class Top {
    private String name;
    private String image;
    private String ban;

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

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public Top(String name, String image, String ban) {

        this.name = name;
        this.image = image;
        this.ban = ban;
    }

    public Top() {

    }
}
