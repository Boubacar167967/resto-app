package com.b1707b.cours.resto_app.reclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.b1707b.cours.resto_app.R;

public class DetailHolder extends RecyclerView.ViewHolder {
    public DetailHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.one_row_detail,parent,false));
    }
}
