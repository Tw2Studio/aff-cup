package com.tw2.affsuzukicup.model;

public class BXH {
    private String diem;
    private String heso;
    private String image;
    private String name;
    private String stt;
    private String tran;

    public BXH() {
    }

    public BXH(String diem, String heso, String image, String name, String stt, String tran) {
        this.diem = diem;
        this.heso = heso;
        this.image = image;
        this.name = name;
        this.stt = stt;
        this.tran = tran;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public String getHeso() {
        return heso;
    }

    public void setHeso(String heso) {
        this.heso = heso;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getTran() {
        return tran;
    }

    public void setTran(String tran) {
        this.tran = tran;
    }
}
