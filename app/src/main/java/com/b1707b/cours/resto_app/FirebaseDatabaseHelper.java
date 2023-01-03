package com.b1707b.cours.resto_app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReferenceMenu;
    public FirebaseDatabaseHelper() {
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReferenceMenu = mFirebaseDatabase.getReference("menu");
            DatabaseReference reference = mDatabaseReferenceMenu.child("diner").child("32");
    }
    public void readData(){
        mDatabaseReferenceMenu.addValueEventListener(new ValueEventListener() {
            List<String> mList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("trrrrrtrttrt",snapshot.getChildrenCount()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}