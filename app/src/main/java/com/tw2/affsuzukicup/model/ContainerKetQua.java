package com.tw2.affsuzukicup.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerKetQua {

    @NonNull
    @SerializedName("tran")
    public List<KetQua> tran;

    @NonNull
    @SerializedName("ngay")
    private String ngay;

    public ContainerKetQua(List<KetQua> tran, String ngay) {
        this.tran = tran;
        this.ngay = ngay;
    }

    public ContainerKetQua() {
    }

    public List<KetQua> getTran() {
        return tran;
    }

    public void setTran(List<KetQua> tran) {
        this.tran = tran;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
