package com.example.ab.personalfinancemanager;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ListInterface {

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<ListResArray> rlist(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<PayRequest> tpay(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<ViewPay> vpay(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<CGroup> cgroup(@Body JsonObject jsonObject);


    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<VGroup> vgroup(@Body JsonObject jsonObject);


}
