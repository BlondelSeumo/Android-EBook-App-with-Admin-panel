
package com.divinetechs.ebooksapp.Model.BookModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("b_id")
    @Expose
    private String bId;
    @SerializedName("b_title")
    @Expose
    private String bTitle;
    @SerializedName("b_description")
    @Expose
    private String bDescription;
    @SerializedName("is_paid")
    @Expose
    private String isPaid;
    @SerializedName("sample_b_url")
    @Expose
    private String sampleBUrl;
    @SerializedName("b_url")
    @Expose
    private String bUrl;
    @SerializedName("b_price")
    @Expose
    private String bPrice;
    @SerializedName("fcat_id")
    @Expose
    private String fcatId;
    @SerializedName("b_image")
    @Expose
    private String bImage;
    @SerializedName("readcnt")
    @Expose
    private String readcnt;
    @SerializedName("download")
    @Expose
    private String download;
    @SerializedName("is_feature")
    @Expose
    private String isFeature;
    @SerializedName("b_status")
    @Expose
    private String bStatus;
    @SerializedName("fa_id")
    @Expose
    private String faId;
    @SerializedName("b_date")
    @Expose
    private String bDate;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    @SerializedName("cat_date")
    @Expose
    private String catDate;
    @SerializedName("cat_status")
    @Expose
    private String catStatus;
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
    @SerializedName("avg_rating")
    @Expose
    private String avg_rating;

    public String getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(String avg_rating) {
        this.avg_rating = avg_rating;
    }

    public String getBId() {
        return bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

    public String getBTitle() {
        return bTitle;
    }

    public void setBTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getBDescription() {
        return bDescription;
    }

    public void setBDescription(String bDescription) {
        this.bDescription = bDescription;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getSampleBUrl() {
        return sampleBUrl;
    }

    public void setSampleBUrl(String sampleBUrl) {
        this.sampleBUrl = sampleBUrl;
    }

    public String getBUrl() {
        return bUrl;
    }

    public void setBUrl(String bUrl) {
        this.bUrl = bUrl;
    }

    public String getBPrice() {
        return bPrice;
    }

    public void setBPrice(String bPrice) {
        this.bPrice = bPrice;
    }

    public String getFcatId() {
        return fcatId;
    }

    public void setFcatId(String fcatId) {
        this.fcatId = fcatId;
    }

    public String getBImage() {
        return bImage;
    }

    public void setBImage(String bImage) {
        this.bImage = bImage;
    }

    public String getReadcnt() {
        return readcnt;
    }

    public void setReadcnt(String readcnt) {
        this.readcnt = readcnt;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getIsFeature() {
        return isFeature;
    }

    public void setIsFeature(String isFeature) {
        this.isFeature = isFeature;
    }

    public String getBStatus() {
        return bStatus;
    }

    public void setBStatus(String bStatus) {
        this.bStatus = bStatus;
    }

    public String getFaId() {
        return faId;
    }

    public void setFaId(String faId) {
        this.faId = faId;
    }

    public String getBDate() {
        return bDate;
    }

    public void setBDate(String bDate) {
        this.bDate = bDate;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public String getCatDate() {
        return catDate;
    }

    public void setCatDate(String catDate) {
        this.catDate = catDate;
    }

    public String getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(String catStatus) {
        this.catStatus = catStatus;
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
