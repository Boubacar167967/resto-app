package com.b1707b.cours.resto_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.b1707b.cours.resto_app.reclerview.DetailAdapter;
import com.b1707b.cours.resto_app.reclerview.DetailDont;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    private ImageView mImageQrCode;
    private ArrayList<DetailDont> mDetailDonts = new ArrayList<>();
    DetailAdapter gmailAdapter;
    String numCart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeActivity activity = (HomeActivity) getActivity();
        assert activity != null;
        numCart = activity.getNumCart().toUpperCase(Locale.ROOT);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = activity.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        //--------------
        {
            String url = "http://" + LoginActivity.getIpAdd() + "/memoir/server/getDetail.php";
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("numCart", numCart);
            Log.d("numCarteeeeeeee", "onCreateView: "+numCart);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray array = (JSONArray) jsonObject.get("response");
                    for (int i=0;i<array.length();i++){
                        JSONObject object = (JSONObject) array.get(i);
                        Log.d("numCarteeeeeeee", "onCreateView: "+object.get("receive"));
                        if (object.get("sender").equals(numCart)){
                                mDetailDonts.add(new DetailDont("Envoie à "+object.get("receive"),""+object.get("data_transaction"),object.get("nbr_ticket")+" "+object.get("type")));
                        }
                        if (object.get("receive").equals(numCart)){

                            mDetailDonts.add(new DetailDont("Reçu de "+object.get("sender"),""+object.get("data_transaction"),object.get("nbr_ticket")+" "+object.get("type")));
                        }
                    }
                    mRecyclerView = view.findViewById(R.id.fragment_home_riclerView);
                    gmailAdapter = new DetailAdapter(mDetailDonts, getContext());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(gmailAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show()) {
                @NonNull
                @Override
                protected Map<String, String> getParams() {
                    return hashMap;
                }
            };
            MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        }
        //--------------
        mImageQrCode = view.findViewById(R.id.fragment_home_imgQrCode);
        generateQR(numCart);
       TextView viewPetiDeuj =  view.findViewById(R.id.fhNbrPetDej);
       TextView viewRepas = view.findViewById(R.id.fhNbrRepas);
       String petitD = sharedPreferences.getString("nbr_pet_deuj","");
       String repas = sharedPreferences.getString("nbr_repas","");
       repas = repas+" Repas";
       petitD = petitD+" P'Dej";
       viewPetiDeuj.setText(petitD);
       viewRepas.setText(repas);
        return view;
    }

    private void generateQR(String numCart) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(numCart, BarcodeFormat.QR_CODE, 150, 150);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            mImageQrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}