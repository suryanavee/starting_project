package com.example.ab.personalfinancemanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


    public class ListResArray {

        @SerializedName("data")
        @Expose
        private List<ListRes> data = null;
        @SerializedName("response")
        @Expose
        private String response;

        public List<ListRes> getData() {
            return data;
        }

        public void setData(List<ListRes> data) {
            this.data = data;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

    }
