package com.example.ab.personalfinancemanager;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.json.JSONArray;

import static android.content.Context.MODE_PRIVATE;

public class CreateGroup extends Fragment {


    TextView creategroup, addmember;
    EditText groupname;
    EditText membername;
    EditText cmemberphone;
    Button addmore, savegroup, vgroup;
    ArrayList addmemberarray = new ArrayList();
    JSONArray notebookUsers = new JSONArray();


    @Override
    public View onCreateView(LayoutInflater inflater, android.view.ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_create_group, container, false);






        creategroup = view.findViewById(R.id.creategroup);
        addmember = view.findViewById(R.id.addmember);
        groupname = view.findViewById(R.id.groupname);
        membername=view.findViewById(R.id.membername);
        cmemberphone = view.findViewById(R.id.memberphone);
        addmore = view.findViewById(R.id.addmore);
        savegroup = view.findViewById(R.id.savegroup);
        vgroup = view.findViewById(R.id.vgroup);

        SharedPreferences sp =
                getActivity().getSharedPreferences("Login", MODE_PRIVATE);

        final String umobile = sp.getString("Mobile", "");

        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject user = new JSONObject();

                try {
                    user.put("name", membername.getText().toString());//Here For just testing I am sending Manual Values.


                    user.put("mobile", cmemberphone.getText().toString());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                notebookUsers.put(user);


                /*String a = cmemberphone.getText().toString();*/
                addmemberarray.add(notebookUsers);
                Log.i("srrsy", addmemberarray.toString());
            }
        });

        savegroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Toast.makeText(CreateGroup.this, ""+groupname, Toast.LENGTH_SHORT).show();

                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.put("members", notebookUsers);
                    jsonObject.put("gname", groupname.getText().toString().trim());
                    jsonObject.put("admin_id", umobile);
                    jsonObject.put("action", "create_group");

                    JsonObject object = new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
                    Log.i("gggg", object.toString());

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://androindian.com/apps/fm/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ListInterface groupInterface = retrofit.create(ListInterface.class);

                    Call<CGroup> cGroupCall = groupInterface.cgroup(object);

                    cGroupCall.enqueue(new Callback<CGroup>() {
                        @Override
                        public void onResponse(Call<CGroup> call, Response<CGroup> response) {
                            if (response.body().getResponse().equalsIgnoreCase("success")) {
                                Toast.makeText(getActivity(), "" + response.body().getCallBack(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "" + response.body().getCallBack(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<CGroup> call, Throwable t) {

                            Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        vgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ViewCreateGroup.class);
                startActivity(intent);
            }
        });


        return  view;
    }
    }


