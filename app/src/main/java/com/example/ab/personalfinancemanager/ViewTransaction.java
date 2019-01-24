package com.example.ab.personalfinancemanager;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.ab.personalfinancemanager.databinding.ActivityViewTransactionBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewTransaction extends AppCompatActivity {

    ActivityViewTransactionBinding viewTransactionBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewTransactionBinding=DataBindingUtil.setContentView(ViewTransaction.this,R.layout.activity_view_transaction);


        SharedPreferences sp =
                getSharedPreferences("Login", MODE_PRIVATE);

        final String vtmobile=sp.getString("Mobile","");

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_trans_as_personal");
            jsonObject.put("gu_id",vtmobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://androindian.com/apps/fm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ListInterface vtlistinterface=retrofit.create(ListInterface.class);

        Call<ViewPay> viewPayCall=vtlistinterface.vpay(object);

        viewPayCall.enqueue(new Callback<ViewPay>() {
            @Override
            public void onResponse(Call<ViewPay> call, Response<ViewPay> response) {
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ViewTransaction.this,LinearLayoutManager.VERTICAL,false);
                viewTransactionBinding.vtrec.setLayoutManager(linearLayoutManager);
                viewTransactionBinding.vtrec.setAdapter(new CustomViewTransaction(ViewTransaction.this,response.body().getData()));
            }

            @Override
            public void onFailure(Call<ViewPay> call, Throwable t) {

                Toast.makeText(ViewTransaction.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
