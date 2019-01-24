package com.example.ab.personalfinancemanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VGroupArray {

    @SerializedName("gid")
    @Expose
    private String gid;
    @SerializedName("gname")
    @Expose
    private String gname;
    @SerializedName("gusers")
    @Expose
    private String gusers;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("gadmin")
    @Expose
    private String gadmin;
    @SerializedName("gstatus")
    @Expose
    private String gstatus;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGusers() {
        return gusers;
    }

    public void setGusers(String gusers) {
        this.gusers = gusers;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getGadmin() {
        return gadmin;
    }

    public void setGadmin(String gadmin) {
        this.gadmin = gadmin;
    }

    public String getGstatus() {
        return gstatus;
    }

    public void setGstatus(String gstatus) {
        this.gstatus = gstatus;
    }

}
