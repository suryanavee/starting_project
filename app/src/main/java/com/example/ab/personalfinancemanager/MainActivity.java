package com.example.ab.personalfinancemanager;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



    EditText id,pass;
    Button newuser,login;
    String url="http://androindian.com/apps/fm/api.php";

    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);



        id=findViewById(R.id.mobile);
        pass=findViewById(R.id.password);
        newuser=findViewById(R.id.newUser);
        login=findViewById(R.id.login);



        awesomeValidation.addValidation(this, R.id.mobile, "^[2-9]{2}[0-9]{8}$", R.string.nameerror);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z]).{8,}";
        awesomeValidation.addValidation(this, R.id.password, regexPassword, R.string.err_password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitForm();

            }
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Registration.class);
                intent.putExtra("id",id.getText().toString());
                intent.putExtra("pass",pass.getText().toString());
                startActivity(intent);

            }
        });
    }

    private class LoginUser extends
            AsyncTask<String,String,String> {
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Content loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... param) {
            JSONObject j2=JosnFunctions.InsertValue(url,param[0]);
            return j2.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try {
                JSONObject j3=new JSONObject(s.toString());
                String res=j3.getString("response");
                if(res.equalsIgnoreCase("success")){

                    Toast.makeText(MainActivity.this, "jhbj", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp=getSharedPreferences("Login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Mobile",id.getText().toString());
                    editor.putString("Pass",pass.getText().toString());
                    editor.commit();

                    Intent intent=new Intent(MainActivity.this,HomeFragment.class);

                    startActivity(intent);
                    finish();

                }else if(res.equalsIgnoreCase("failed")){
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("mobile",id.getText().toString().trim());
                jsonObject.put("pswrd",pass.getText().toString().trim());
                jsonObject.put("action","login_user");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LoginUser loginUser=new LoginUser();
            loginUser.execute(jsonObject.toString());

            //process the data further
        }
    }
}
