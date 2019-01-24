package com.example.ab.personalfinancemanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VGroup
{
    @SerializedName("data")

    @Expose

    private List<VGroupArray> data = null;
        @SerializedName("response")
        @Expose
        private String response;

        public List<VGroupArray> getData() {
            return data;
        }

        public void setData(List<VGroupArray> data) {
            this.data = data;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

}