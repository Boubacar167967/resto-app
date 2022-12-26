package com.b1707b.cours.resto_app.reclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.b1707b.cours.resto_app.R;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailHolder> {
   private ArrayList<DetailDont> mDetailDontsArrayAdapter;
   Context mContext ;
   private TextView mViewNatureTransaction,mViewDate,mViewMontant;

    public DetailAdapter(ArrayList<DetailDont> emailArrayAdapter, Context context) {
        mDetailDontsArrayAdapter = emailArrayAdapter;
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
        mViewMontant = holder.itemView.findViewById(R.id.oneRow_price);

        String nature = mDetailDontsArrayAdapter.get(position).getNature();
        String date = mDetailDontsArrayAdapter.get(position).getDate();
        String montant = mDetailDontsArrayAdapter.get(position).getMontant();
        mViewNatureTransaction.setText(nature);
        mViewDate.setText(date);
        mViewMontant.setText(montant);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDetailDontsArrayAdapter.size();
    }
}
