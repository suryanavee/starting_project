package com.example.ab.personalfinancemanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class CustomViewTransaction extends RecyclerView.Adapter<CustomViewTransaction.MyViewHolder> {



    Context context;
    List<ViewPayArray> resdata;

    public CustomViewTransaction(ViewTransaction viewTransaction, List<ViewPayArray> data) {

        resdata=data;
        context=viewTransaction;
    }


    @Override
    public CustomViewTransaction.MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_transaction,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CustomViewTransaction.MyViewHolder myViewHolder, int i) {

    myViewHolder.vtmobile.setText(resdata.get(i).getGuid());
    myViewHolder.vtreason.setText(resdata.get(i).getReason());
    myViewHolder.vtdate.setText(resdata.get(i).getDate());
    myViewHolder.vtamount.setText(resdata.get(i).getAmount());

    }

    @Override
    public int getItemCount() {
        return resdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vtmobile,vtreason,vtdate,vtamount;

        public MyViewHolder( View itemView) {
            super(itemView);

            vtmobile=itemView.findViewById(R.id.vtmobile);
            vtreason=itemView.findViewById(R.id.vtreason);
            vtdate=itemView.findViewById(R.id.vtdate);
            vtamount=itemView.findViewById(R.id.vtamount);

        }
    }
}
