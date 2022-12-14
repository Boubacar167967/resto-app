package com.b1707b.cours.resto_app.reclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b1707b.cours.resto_app.R;
import com.b1707b.cours.resto_app.reclerview.Email;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailHolder> {
   private ArrayList<Email> mEmailArrayAdapter;
   Context mContext ;
   private TextView mViewNatureTransaction,mViewDate,mViewPrice;

    public DetailAdapter(ArrayList<Email> emailArrayAdapter, Context context) {
        mEmailArrayAdapter = emailArrayAdapter;
        mContext  = context;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new DetailHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position){
        mViewNatureTransaction = holder.itemView.findViewById(R.id.oneRow_nature);
        mViewDate = holder.itemView.findViewById(R.id.oneRow_date);
        mViewPrice = holder.itemView.findViewById(R.id.oneRow_price);
        String name = mEmailArrayAdapter.get(position).getNameSender();
        String object = mEmailArrayAdapter.get(position).getObject();
        String content = mEmailArrayAdapter.get(position).getMessageContent();
        Boolean isRead = mEmailArrayAdapter.get(position).getRead();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEmailArrayAdapter.size();
    }
}
