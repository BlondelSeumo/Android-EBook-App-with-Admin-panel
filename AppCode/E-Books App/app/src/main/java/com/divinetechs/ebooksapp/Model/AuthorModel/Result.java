
package com.divinetechs.ebooksapp.Model.AuthorModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("a_id")
    @Expose
    private String aId;
    @SerializedName("a_title")
    @Expose
    private String aTitle;
    @SerializedName("a_bio")
    @Expose
    private String aBio;
    @SerializedName("a_image")
    @Expose
    private String aImage;
    @SerializedName("a_status")
    @Expose
    private String aStatus;
    @SerializedName("a_address")
    @Expose
    private String aAddress;

    public String getaAddress() {
        return aAddress;
    }

    public void setaAddress(String aAddress) {
        this.aAddress = aAddress;
    }

    public String getAId() {
        return aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getATitle() {
        return aTitle;
    }

    public void setATitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getABio() {
        return aBio;
    }

    public void setABio(String aBio) {
        this.aBio = aBio;
    }

    public String getAImage() {
        return aImage;
    }

    public void setAImage(String aImage) {
        this.aImage = aImage;
    }

    public String getAStatus() {
        return aStatus;
    }

    public void setAStatus(String aStatus) {
        this.aStatus = aStatus;
    }

}
