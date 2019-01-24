package com.example.ab.personalfinancemanager;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ViewCreateGroup extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_create_group);

        recyclerView=findViewById(R.id.recvg);
        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        String mobile = sp.getString("Mobile", "");
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_groups");
            jsonObject.put("member","9999999991");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://androindian.com/apps/fm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            ListInterface listInterface=retrofit.create(ListInterface.class);
         Call<VGroup> vGroupCall=listInterface.vgroup(object);

        vGroupCall.enqueue(new Callback<VGroup>() {
            @Override
            public void onResponse(Call<VGroup> call, Response<VGroup> response) {
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ViewCreateGroup.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(new CustomViewGroup(ViewCreateGroup.this,response.body().getData()));
            }

            @Override
            public void onFailure(Call<VGroup> call, Throwable t) {
                Toast.makeText(ViewCreateGroup.this, ""+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
