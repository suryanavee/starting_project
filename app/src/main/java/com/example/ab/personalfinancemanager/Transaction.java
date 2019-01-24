package com.example.ab.personalfinancemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ab.personalfinancemanager.databinding.ActivityTransactionBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Transaction extends AppCompatActivity {


    ActivityTransactionBinding binding;



    String credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding=DataBindingUtil.setContentView(Transaction.this,R.layout.activity_transaction);

        SharedPreferences sp =
                getSharedPreferences("Login", MODE_PRIVATE);

        final String umobile=sp.getString("Mobile","");

        binding.trg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.trbdr){
              credit="D" ;
                }else{
                    credit="C";
                }
            }
        });



      binding.tpay.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              JSONObject jsonObject =new JSONObject();

              try {
                  jsonObject.put("mobile_no",umobile);
                  jsonObject.put("gu_id",umobile);
                  jsonObject.put("reason",binding.treason.getText().toString().trim());
                  jsonObject.put("bdate",binding.tbdate.getText().toString().trim());
                  jsonObject.put("amount",binding.tamount.getText().toString().trim());
                  jsonObject.put("cr_or_dr",credit);
                  jsonObject.put("action","add_trans_as_personal");



                  JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                  Retrofit retrofit = new Retrofit.Builder()
                          .baseUrl("http://androindian.com/apps/fm/")
                          .addConverterFactory(GsonConverterFactory.create())
                          .build();

                  ListInterface payInterface =retrofit.create(ListInterface.class);
                  Call<PayRequest> payRequestCall=payInterface.tpay(object);
                  payRequestCall.enqueue(new Callback<PayRequest>() {
                      @Override
                      public void onResponse(Call<PayRequest> call, Response<PayRequest> response) {

                          if (response.body().getResponse().equalsIgnoreCase("success"))
                          {
                              Toast.makeText(Transaction.this, ""+response.body().getCallBack(), Toast.LENGTH_SHORT).show();
                          }
                          else
                          {
                              Toast.makeText(Transaction.this, ""+response.body().getCallBack(), Toast.LENGTH_SHORT).show();
                          }
                      }

                      @Override
                      public void onFailure(Call<PayRequest> call, Throwable t) {

                      }
                  });



              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      });

      binding.vt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(getApplicationContext(),ViewTransaction.class);
              startActivity(intent);
          }
      });


    }
}
