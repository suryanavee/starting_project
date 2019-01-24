package com.example.ab.personalfinancemanager;


import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetworkCheck extends Application {
    NetworkConnectivity listner;
    private static NetworkCheck mInstance;
    IntentFilter filter;
    private boolean isregistered;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        listner=new NetworkConnectivity();


    }

    public void setConnectivityListener(NetworkConnectivity.ConnectivityReceiverListener listener) {
        NetworkConnectivity.connectivityReceiverListener = listener;
        registerReceiver(listner,filter);
        isregistered=true;
    }
    public void resetConnectivityListener(NetworkConnectivity.ConnectivityReceiverListener listener){
        if (isregistered) {
            isregistered=false;
            NetworkConnectivity.connectivityReceiverListener = null;
            unregisterReceiver(listner);
        }
    }

    public static NetworkCheck getInstance() {
        return mInstance;
    }


}

