package com.example.ab.personalfinancemanager;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class Home extends android.app.Fragment {



    Spinner stype;
    EditText reason, amount,startdate,enddate;
    Button save,vexp,cgroup,vgroup;
    JSONObject j1 = null;
    String url = "http://androindian.com/apps/fm/api.php";


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {



        View view =inflater.inflate(R.layout.activity_home,container,false);



        reason = view.findViewById(R.id.reason);
        amount = view.findViewById(R.id.amount);
        save = view.findViewById(R.id.save);
        startdate=view.findViewById(R.id.startdate);
        enddate=view.findViewById(R.id.enddate);
        vexp=view.findViewById(R.id.vexp);
        cgroup=view.findViewById(R.id.cgroup);
        vgroup=view.findViewById(R.id.vgroup);





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp =
                        getActivity().getSharedPreferences("Login", MODE_PRIVATE);
                String mobile = sp.getString("Mobile", "");

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("stype", "S");
                    jsonObject.put("reason", reason.getText().toString().trim());
                    jsonObject.put("amount", amount.getText().toString().trim());
                    jsonObject.put("start_date", startdate.getText().toString().trim());
                    jsonObject.put("end_date", enddate.getText().toString().trim());
                    jsonObject.put("mobile",mobile);
                    //wrrite shared
                    jsonObject.put("action", "put_saving");




                } catch (JSONException e) {
                    e.printStackTrace();

                }

                SaveData saveData = new SaveData();
                saveData.execute(jsonObject.toString());


            }
        });


    vexp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            android.app.FragmentTransaction fr2=getFragmentManager().beginTransaction();
            ViewSaving secondFrag=new ViewSaving();
            fr2.addToBackStack("");
            fr2.replace(R.id.frame,secondFrag);
            fr2.commit();
        }
    });

    cgroup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            android.app.FragmentTransaction fr2=getFragmentManager().beginTransaction();
            CreateGroup createGroup=new CreateGroup();
            fr2.addToBackStack("");
            fr2.replace(R.id.frame,createGroup);
            fr2.commit();
        }
    });
    vgroup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getActivity(),ViewCreateGroup.class);
            startActivity(intent);

        }
    });
        return view;

        }



    private class SaveData extends AsyncTask<String,String,String> {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Content Loading");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... param) {

            j1=JosnFunctions.InsertValue(url,param[0]);

            return j1.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try {
                JSONObject j2 = new JSONObject(j1.toString());

                String res1=j2.getString("response");

                if(res1.equalsIgnoreCase("success")){
                    String res2=j2.getString("call_back");
                    Toast.makeText(getActivity(), ""+res2, Toast.LENGTH_SHORT).show();

                    reason.setText("");
                    amount.setText("");
                    startdate.setText("");
                    enddate.setText("");

                }
                else
                {
                    /*Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();*/
                }
            } catch (JSONException e) {


            }
        }
    }

    }
