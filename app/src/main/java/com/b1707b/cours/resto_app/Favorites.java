package com.b1707b.cours.resto_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
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

    public void setResponse(String s) {
        this.mResponse = s;
    }

    public String getResponse() {
        return this.mResponse;
    }

    public Favorites(int id_user, int id_plats, ImageView view, Context context) {
        this.id_user = id_user;
        this.id_plats = id_plats;
        mImageView = view;
        mContext = context;
    }

    public void makeFavorite() {
        final boolean[] is_cliked = {false};
        Map<String, String> map = new HashMap<>();
        map.put("verifIdUser", "" + id_user);
        map.put("verifIdPlats", "" + id_plats);
        getData(map);
        Toast.makeText(mContext, "" + getResponse(), Toast.LENGTH_SHORT).show();
        if (Objects.equals("true", "true")) {
            mImageView.setImageResource(R.drawable.ic_baseline_favorite_24);
            Toast.makeText(mContext, "je suis dans le if", Toast.LENGTH_SHORT).show();
        } else {
            mImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_lastFav = "";
                //Log.d("myresponse",getResponse());
                if (!is_cliked[0] && Objects.equals(getResponse(), "false")) {
                    Map<String, String> mapMakFavorite = new HashMap<>();
                    mapMakFavorite.put("CreateFavIdUser", "" + id_user);
                    mapMakFavorite.put("CreateFavIdPlats", "" + id_plats);
                    // id_lastFav =  getData(mapMakFavorite);
                    is_cliked[0] = true;
                } else {
                    Map<String, String> mapAlterFavorite = new HashMap<>();
                    mapAlterFavorite.put("AlterIdUser", "" + id_user);
                    mapAlterFavorite.put("AlterIdPlats", "" + id_plats);
                    mapAlterFavorite.put("AlterIdUserFavo", id_lastFav);
                    getData(mapAlterFavorite);
                    is_cliked[0] = false;
                }
            }
        });
    }

    private void getData(Map<String, String> hashMap) {
        String url = "http://" + LoginActivity.getIpAdd() + "/memoir/server/getFavorite.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
