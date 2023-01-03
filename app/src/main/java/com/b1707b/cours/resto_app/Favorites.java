package com.b1707b.cours.resto_app;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Favorites {
    private String mResponse;
    private int id_user;
    private int id_plats;
    private ImageView mImageView;
    private Context mContext;
    private int id_menu;

    public Favorites(int id_user, int id_plats, int id_menu, ImageView view, Context context) {
        this.id_user = id_user;
        this.id_plats = id_plats;
        this.id_menu = id_menu;
        mImageView = view;
        mContext = context;

    }

    public void makeFavorite(String s) {
        final boolean[] is_cliked = {false};
        Map<String, String> map = new HashMap<>();
        map.put("verifIdUser", "" + id_user);
        map.put("verifIdPlats" + s, "" + id_plats);
        map.put("verifIdMenu", "" + id_menu);
        getData(map, s);
    }

    private void getData(Map<String, String> hashMap, String s) {
        String url = "http://" + LoginActivity.getIpAdd() + "/memoir/server/getFavorite.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(response);
                String responsePhp = jsonObject.getString("response");
                Log.d("tyyyyyytytytyty", "getData: " + response);
                if (Objects.equals(responsePhp, "true")) {
                    mImageView.setImageResource(R.drawable.ic_baseline_favorite_24);
                    Toast.makeText(mContext, "je suis dans le if", Toast.LENGTH_SHORT).show();
                } else {
                    mImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
                mImageView.setOnClickListener(v -> {
                    //Log.d("clikeddddddddd",responsePhp);
                    String id_lastFav = "";
                    if (Objects.equals(responsePhp, "false")) {
                        Map<String, String> mapMakFavorite = new HashMap<>();
                        mapMakFavorite.put("CreateFavIdUser", "" + id_user);
                        mapMakFavorite.put("CreateFavIdPlats" + s, "" + id_plats);
                        mapMakFavorite.put("CreateFavIdMenu", "" + id_menu);
                        getData(mapMakFavorite, s);
                    } else {
                        Map<String, String> mapAlterFavorite = new HashMap<>();
                        mapAlterFavorite.put("AlterIdUser", "" + id_user);
                        mapAlterFavorite.put("AlterIdPlats" + s, "" + id_plats);
                        mapAlterFavorite.put("AlterIdMenu", "" + id_menu);
                        getData(mapAlterFavorite, s);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return hashMap;
            }
        };
        MySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }
}
