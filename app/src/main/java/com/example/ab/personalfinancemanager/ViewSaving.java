package com.example.ab.personalfinancemanager;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class ViewSaving extends android.app.Fragment {


    TextView viewstype,viewamount,viewstartdate,viewenddate,viewreason;

    String url="http://androindian.com/apps/fm/api.php";

    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,Bundle savedInstanceState) {

        View savingview=inflater.inflate(R.layout.activity_view_saving,container,false);


        listView=savingview.findViewById(R.id.list);

        SharedPreferences sp =
                getActivity().getSharedPreferences("Login", MODE_PRIVATE);
        String mobile = sp.getString("Mobile", "");

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_savings");
            jsonObject.put("stype","S");
            jsonObject.put("user_id",mobile

            );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://androindian.com/apps/fm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ListInterface listInterface=retrofit.create(ListInterface.class);
        Call<ListResArray> listResCall=listInterface.rlist(object);

        listResCall.enqueue(new Callback<ListResArray>() {
            @Override
            public void onResponse(Call<ListResArray> call, Response<ListResArray> response) {
                if(response.body().getResponse().equalsIgnoreCase("empty"))
                {
                    Toast.makeText(getActivity(), "No Records Found", Toast.LENGTH_SHORT).show();
                }else {
                    Log.i("abcd", response.toString());
                    Log.i("xyz", call.toString());
                    listView.setAdapter(new CustomAdapter(getActivity(), response.body().getData()));
                }

            }

            @Override
            public void onFailure(Call<ListResArray> call, Throwable t) {

                Toast.makeText(getActivity(), ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        return savingview;
    }



}
