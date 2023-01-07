package com.b1707b.cours.resto_app.favorite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.b1707b.cours.resto_app.HomeActivity;
import com.b1707b.cours.resto_app.LoginActivity;
import com.b1707b.cours.resto_app.MySingleton;
import com.b1707b.cours.resto_app.R;
import com.b1707b.cours.resto_app.functions.Tools;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
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
                if (!snapshot.exists()) {
                    addMenu(menu, Integer.parseInt(id_menu)).addOnSuccessListener(unused -> {
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
                    savaNbrFavorite(lib,nbrFavorite,""+id_menu);
                }else {
                    view.setText(0+" likes");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void savaNbrFavorite(String lib,int nbr_like,String id_menu){
        {
            String url = "http://"+ LoginActivity.getIpAdd()+"/memoir/server/savFavorite.php";
            Map<String,String> hashMap = new HashMap<>();
            hashMap.put("sav_fav_nbr_like",nbr_like+"");
            hashMap.put("sav_fav_id_menu",id_menu);
            hashMap.put("sav_fav_lib",lib);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, error -> Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_SHORT).show()){
                @NonNull
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return hashMap;
                }
            };
            MySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
        }
    }
}
