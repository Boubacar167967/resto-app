package com.b1707b.cours.resto_app.favorite;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.b1707b.cours.resto_app.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class FirebaseDatabase {
    private static DatabaseReference mReference;
    private Context mContext;

    public FirebaseDatabase(Context context) {
        mReference = com.google.firebase.database.FirebaseDatabase.getInstance("https://resto-app-721b0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("menu");
        mContext = context;
    }

    public static Task<Void> addMenu(Menu menu, int id_menu) {

        return mReference.child("" + id_menu).setValue(menu);
    }

    public void verifyExistKeyMenu(Menu menu, String id_menu) {
        final boolean[] mBoolean = {false};
        mReference.child(id_menu).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(mContext, "Cette menu exist deja", Toast.LENGTH_SHORT).show();
                } else {
                    addMenu(menu, Integer.parseInt(id_menu)).addOnSuccessListener(unused -> {
                        Toast.makeText(mContext, "Insersion a reussit", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(mContext, "Insersion a fail", Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("errrorrr", error.getDetails());
            }
        });
    }

    public void makeFavorite(ImageView view_btn, String num_card, String id_menu, String lib) {
        loadChange(view_btn,num_card,id_menu,lib);
        view_btn.setOnClickListener(view -> {
            mReference.child(id_menu).child(lib).child(num_card).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        mReference.child(id_menu).child(lib).child(num_card).removeValue();
                        loadChange(view_btn,num_card,id_menu,lib);
                    } else{
                        mReference.child(id_menu).child(lib).child(num_card).setValue("1");
                        loadChange(view_btn,num_card,id_menu,lib);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }
    public void loadChange(ImageView view_btn, String num_card, String id_menu, String lib){
        mReference.child(id_menu).child(lib).child(num_card).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    view_btn.setImageResource(R.drawable.ic_baseline_favorite_24);
                } else
                    view_btn.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void loadNumberFavorite(TextView view,String num_card, String id_menu, String lib){
        mReference.child(id_menu).child(lib).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    int nbrFavorite = (int) (snapshot.getChildrenCount()-1);
                    view.setText(nbrFavorite+" likes");
                }else {
                    view.setText(0+" likes");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
