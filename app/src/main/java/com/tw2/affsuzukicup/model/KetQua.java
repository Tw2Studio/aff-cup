package com.tw2.affsuzukicup.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class KetQua {
    @NonNull
    @SerializedName("imageA")
    private String imageA;

    @NonNull
    @SerializedName("imageB")
    private String imageB;

    @NonNull
    @SerializedName("nameA")
    private String nameA;

    @NonNull
    @SerializedName("nameB")
    private String nameB;

    @NonNull
    @SerializedName("time")
    private String time;

    public KetQua() {
    }

    public String getImageA() {
        return imageA;
    }

    public void setImageA(String imageA) {
        this.imageA = imageA;
    }

    public String getImageB() {
        return imageB;
    }

    public void setImageB(String imageB) {
        this.imageB = imageB;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public KetQua(String imageA, String imageB, String nameA, String nameB, String time) {

        this.imageA = imageA;
        this.imageB = imageB;
        this.nameA = nameA;
        this.nameB = nameB;
        this.time = time;
    }
}
