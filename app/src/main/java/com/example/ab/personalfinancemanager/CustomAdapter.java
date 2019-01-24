package com.example.ab.personalfinancemanager;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<ListRes> resdata;
    public CustomAdapter(Context activity, List<ListRes> data) {

        context=activity;
        resdata=data;
    }

    @Override
    public int getCount() {
        return resdata.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View Rowview= inflater.inflate(R.layout.custom,null);

        TextView t1=Rowview.findViewById(R.id.viewstype);
        TextView t2=Rowview.findViewById(R.id.viewreason);
        TextView t3=Rowview.findViewById(R.id.viewamount);
        TextView t4=Rowview.findViewById(R.id.viewstartdate);
        TextView t5=Rowview.findViewById(R.id.viewenddate);

        t1.setText(resdata.get(i).getStype());
        t2.setText(resdata.get(i).getReason());
        t3.setText(resdata.get(i).getAmount());
        t4.setText(resdata.get(i).getDatetime());
        t5.setText(resdata.get(i).getEnddate());

        return Rowview;
    }
}
