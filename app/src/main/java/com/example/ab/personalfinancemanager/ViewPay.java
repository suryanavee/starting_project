package com.example.ab.personalfinancemanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewPay {

    @SerializedName("data")
    @Expose
    private List<ViewPayArray> data = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<ViewPayArray> getData() {
        return data;
    }

    public void setData(List<ViewPayArray> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
