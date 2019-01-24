package com.example.ab.personalfinancemanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {

    EditText fname, lname, remailid, rmobileno, rpass, cpass;
    Button signup;

    JSONObject j1=null;
    String url="http://androindian.com/apps/fm/api.php";
    String fullname;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_registration);

        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        remailid = findViewById(R.id.rEmailId);
        rmobileno = findViewById(R.id.rPhone);
        rpass = findViewById(R.id.rpass);
        cpass = findViewById(R.id.cpass);
        signup = findViewById(R.id.signUp);

        fullname=fname.getText().toString()+lname.getText().toString();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.firstName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.rEmailId, Patterns.EMAIL_ADDRESS, R.string.nameerror);

        String regexPassword ="(?=.*[a-z])(?=.*[A-Z]).{8,}";

        awesomeValidation.addValidation(Registration.this, R.id.rpass, regexPassword, R.string.err_password);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();

            }
        });


    }

    private class Register extends AsyncTask<String,String,String> {
        ProgressDialog progressDialog = new ProgressDialog(Registration.this);


        @Override
        protected String doInBackground(String... param) {
            j1=JosnFunctions.InsertValue(url,param[0]);
            return j1.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Content Loading");
            progressDialog.show();

        }




        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try {
                JSONObject j2=new JSONObject(j1.toString());
                /*JSONObject j2 = new JSONObject(s.toString());*/
                String res1 = j2.getString("response");

                if (res1.equalsIgnoreCase("failed")) {
                    String res2 = j2.getString("user");
                    Toast.makeText(Registration.this, "" + res2, Toast.LENGTH_SHORT).show();

                } else if (res1.equalsIgnoreCase("success")) {
                    String res2 = j2.getString("user");

                    Intent intent= new Intent(Registration.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(Registration.this, "" + res2, Toast.LENGTH_SHORT).show();

                } else {
                    String res2 = j2.getString("user");
                    Toast.makeText(Registration.this, "" + res2, Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {

            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("name", fullname.trim());
                jsonObject.put("mobile", rmobileno.getText().toString().trim());
                jsonObject.put("email", remailid.getText().toString().trim());
                jsonObject.put("pswrd", rpass.getText().toString().trim());
                jsonObject.put("action", "register_user");


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Register register = new Register();
            register.execute(jsonObject.toString());
        }
    }

}