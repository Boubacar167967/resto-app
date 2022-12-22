package com.b1707b.cours.resto_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EatFragment extends Fragment {
    private final String url = "http://"+LoginActivity.getIpAdd()+"/memoir/server/data_menu.php";
    private Map<Integer, String> hashMapResto = new HashMap<>();
    private JSONObject mJSONObject;
    private CardView mViewArgentin;
    private CardView mViewCentrale;
    private CardView mViewSelf;
    private CardView mViewEsp;
    private CardView mViewEnsep;
    private CardView mViewEcoleNormal;
    private Bundle mBundle;
    private SharedPreferences mSharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSharedPreferences = getContext().getSharedPreferences("id_plats", Context.MODE_PRIVATE);
        View view = inflater.inflate(R.layout.fragment_eat, container, false);
        mViewArgentin = view.findViewById(R.id.card1);
        mViewCentrale = view.findViewById(R.id.card2);
        mViewSelf = view.findViewById(R.id.card3);
        mViewEsp = view.findViewById(R.id.card4);
        mViewEnsep = view.findViewById(R.id.card5);
        mViewEcoleNormal = view.findViewById(R.id.card6);
        hashMapResto.put(R.id.card1, "Argentin");
        hashMapResto.put(R.id.card2, "Centrale");
        hashMapResto.put(R.id.card3, "Self");
        hashMapResto.put(R.id.card4, "Esp");
        hashMapResto.put(R.id.card5, "Ensetp");
        hashMapResto.put(R.id.card6, "Ecole normale");
        launchMenu(mViewArgentin);
        launchMenu(mViewCentrale);
        launchMenu(mViewEsp);
        launchMenu(mViewEnsep);
        launchMenu(mViewEcoleNormal);
        launchMenu(mViewSelf);
        return view;
    }

    private void launchMenu(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(hashMapResto.get(view.getId()));
                Toast.makeText(getContext(), ""+hashMapResto.get(view.getId()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(String restoName) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    mJSONObject = new JSONObject(response) ;
                    JSONObject jsonObject = null;
                    String date = mJSONObject.getString("dateMenu");
                    //pour le repas
                    jsonObject = mJSONObject.getJSONObject("diner");
                    String nomDiner = jsonObject.getString("nom");
                    String descriptionDiner = jsonObject.getString("description_diner");
                    String imgDiner = jsonObject.getString("img_diner");
                    int id_diner = jsonObject.getInt("id_diner");
                    editor.putInt("id_diner",id_diner);
                    //pour le repas 1
                    jsonObject = mJSONObject.getJSONObject("repas");
                    String nomRepas = jsonObject.getString("nom");
                    String descriptionRepas = jsonObject.getString("description_repas");
                    String imgRepas = jsonObject.getString("img_repas");
                    int id_repas = jsonObject.getInt("id_repas");
                    editor.putInt("id_repas",id_repas);
                    //------------------------------------------------
                    //pour le repas
                    jsonObject = mJSONObject.getJSONObject("diner1");
                    String nomDiner1 = jsonObject.getString("nom1");
                    String description_diner1 = jsonObject.getString("description_diner1");
                    String imgDiner1 = jsonObject.getString("img_diner1");
                    int id_diner1 = jsonObject.getInt("id_diner1");
                    editor.putInt("id_diner1",id_diner1);

                    //pour le repas 1
                    jsonObject = mJSONObject.getJSONObject("repas1");
                    String nomRepas1 = jsonObject.getString("nom1");
                    String descriptionRepas1 = jsonObject.getString("description_repas1");
                    String imgRepas1 = jsonObject.getString("img_repas1");
                    int id_repas1 = jsonObject.getInt("id_repas1");
                    editor.putInt("id_repas1",id_repas1);

                    //------------------------------------------------
                    Log.d("imgRepas1", imgRepas1);
                    HomeActivity activity = (HomeActivity) getActivity();
                    Bundle bundle = new Bundle();
                    FragmentDetailMenu fragmentDetailMenu = new FragmentDetailMenu();
                    bundle.putString("dateMenu",date);
                    bundle.putString("descriptionDiner",descriptionDiner);
                    bundle.putString("nomDiner",nomDiner);
                    bundle.putString("imgDiner",imgDiner);
                    bundle.putString("nomRepas",nomRepas);
                    bundle.putString("descriptionRepas",descriptionRepas);
                    bundle.putString("imgRepas",imgRepas);
                    bundle.putString("nomDiner1",nomDiner1);
                    bundle.putString("imgDiner1",imgDiner1);
                    bundle.putString("description_repas1",description_diner1);
                    bundle.putString("imgRepas1",imgRepas1);
                    bundle.putString("nomRepas1",nomRepas1);
                    bundle.putString("descriptionRepas1",descriptionRepas1);
                    bundle.putString("imgRepas1",imgRepas1);
                    editor.apply();
                    //
                    //lancement du fragment
                    fragmentDetailMenu.setArguments(bundle);
                    assert activity != null;
                    activity.replaceFragment(fragmentDetailMenu);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("restoName", restoName);
                return hashMap;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}