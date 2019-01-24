package com.example.ab.personalfinancemanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class CustomViewGroup extends RecyclerView.Adapter<CustomViewGroup.MyGroupViewholder> {


    Context context;
    List<VGroupArray> groupdata;
    public CustomViewGroup(Context viewGroup, List<VGroupArray> data) {

        groupdata=data;
        context=viewGroup;
    }

    @NonNull
    @Override
    public CustomViewGroup.MyGroupViewholder onCreateViewHolder(@NonNull android.view.ViewGroup viewGroup, int i) {
       View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_group,null);

        return new MyGroupViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewGroup.MyGroupViewholder myGroupViewholder, int i) {

        myGroupViewholder.gname.setText(groupdata.get(i).getGname());
        /*myGroupViewholder.gdate.setText(groupdata.get(i).getDatetime());*/
        /*myGroupViewholder.gmobile.setText(groupdata.get(i).getGusers());*/
        myGroupViewholder.gstatus.setText(groupdata.get(i).getGstatus());


    }

    @Override
    public int getItemCount() {
        return groupdata.size();
    }

    public class MyGroupViewholder extends RecyclerView.ViewHolder {
        TextView gname,gmobile,gstatus;
        public MyGroupViewholder(@NonNull View itemView) {
            super(itemView);

            gname=itemView.findViewById(R.id.tv1);
            gmobile=itemView.findViewById(R.id.tv2);

            gstatus=itemView.findViewById(R.id.tv3);

        }
    }
}
