package com.example.ab.personalfinancemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Splash extends AppCompatActivity implements NetworkConnectivity.ConnectivityReceiverListener {

    private static int SPLASH_TIME_OUT = 500;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        view=findViewById(android.R.id.content);
        NetworkCheck.getInstance().setConnectivityListener(this);



    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected == true) {
           /* AlertDialog.Builder alet = new AlertDialog.Builder(HomeFragment.this);
            alet.setMessage("Net is there");
            alet.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alet.show();*/

            new Handler().postDelayed(new Runnable() {
                @Override

                public void run () {

                    SharedPreferences sp =
                            getSharedPreferences("Login", MODE_PRIVATE);
                    String mobile = sp.getString("Mobile", "");
                    String pass = sp.getString("Pass", "");

                    if (mobile.equals("") && pass.equals("")) {
                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(Splash.this, HomeFragment.class);
                        startActivity(intent);
                        finish();

                    }
                }


            },SPLASH_TIME_OUT);


        } else {

            Snackbar.make(view," No connection",Snackbar.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override

                public void run () {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();


                }
                },SPLASH_TIME_OUT);


        }
    }
}
