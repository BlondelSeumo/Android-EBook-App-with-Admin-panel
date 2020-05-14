
package com.divinetechs.ebooksapp.Model.ReadDowncntModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("readcount")
    @Expose
    private String readcount;
    @SerializedName("download")
    @Expose
    private String download;

    public String getReadcount() {
        return readcount;
    }

    public void setReadcount(String readcount) {
        this.readcount = readcount;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

}
