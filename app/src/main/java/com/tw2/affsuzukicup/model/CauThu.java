package com.tw2.affsuzukicup.model;

public class CauThu {
    private String name;
    private String image;
    private String vitri;

    public CauThu() {
    }

    public CauThu(String name, String image, String vitri) {
        this.name = name;
        this.image = image;
        this.vitri = vitri;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
