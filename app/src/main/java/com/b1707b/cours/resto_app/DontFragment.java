package com.b1707b.cours.resto_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.b1707b.cours.resto_app.databinding.FragmentDontBinding;
import com.b1707b.cours.resto_app.functions.Tools;
import com.google.android.material.textfield.TextInputLayout;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class DontFragment extends Fragment {
    FragmentDontBinding binding;
    private AlertDialog mAlertDialog;

    public DontFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDontBinding.inflate(inflater, container, false);

        binding.fdBtnValider.setOnClickListener(v -> {
            if (Objects.requireNonNull(binding.fdNumCard.getText()).toString().length() < 9) {
                binding.fdNumCard.setError("Le numéro carte doit etre au moins 9 caractere");
                binding.fdNumCard.requestFocus();
            } else {
                String userCard = binding.fdNumCard.getText().toString();
                verifyIsExist(userCard);
            }
        });
        binding.fdQrLaunch.setOnClickListener(v -> {
            scanQR();
        });
        Date dateAndTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("kk:mm:ss", Locale.ENGLISH);
        String time = simpleDateFormat.format(dateAndTime);
        String Date = dateFormat.format(dateAndTime);
        String t1 = "6:00:00 PM";
        String t2 = "7:00:00 PM";
        Log.d("ttttttttttttttttt",""+time);
        return binding.getRoot();
    }

    private void scanQR() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Augmenter volume flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        mLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> mLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("ok", (dialogInterface, i) -> dialogInterface.dismiss()).show();
            verifyIsExist(result.getContents());
        }
    });

    public void verifyIsExist(String s) {
        String url = "http://" + LoginActivity.getIpAdd() + "/memoir/server/scanner.php";
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("numberCartReceive", s);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(response);
                Log.d("dontResponse", "onResponse: " + jsonObject);
                String res = jsonObject.getString("response");
                Toast.makeText(getContext(), "" + res, Toast.LENGTH_SHORT).show();
                if (res.equals("numCard_not_exist")) {
                    new Tools(getContext()).displayAlert("erreur", "Etudiant in trouvable");
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                    builder.setTitle("Continuer");
                    final int[] typeTicket = {0};
                    View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
                    TextInputLayout textInputLayout = view.findViewById(R.id.ldNbrTickets);
                    Button buttonPositive = view.findViewById(R.id.btnPositive);
                    Button buttonNegative = view.findViewById(R.id.btnNegative);
                    String[] type = new String[]{"Petit dejeuner", "Repas"};
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.drawpo, type);
                    AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoComplexeDialog);
                    autoCompleteTextView.setAdapter(adapter);
                    autoCompleteTextView.setOnItemClickListener((adapterView, view1, i, l) -> {

                        if (i == 0) {
                            typeTicket[0] = i;
                        } else {
                            typeTicket[0] = i;
                        }
                    });
                    buttonPositive.setOnClickListener(v -> {
                        if (Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().isEmpty()) {
                            textInputLayout.setError("Donner le nom");
                            textInputLayout.setFocusable(true);
                        } else {
                            Map<String, String> hashMap1 = new HashMap<>();
                            //get the time
                            Date dateAndTime = Calendar.getInstance().getTime();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ss kk:mm:ss", Locale.getDefault());
                            String date = dateFormat.format(dateAndTime);
                            Log.d("tttttttttttttzzzzzzzzzz", "verifyIsExist: "+date);
                            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
                            String SenderNumberCart = sharedPreferences.getString("userNumberCart", "");
                            hashMap1.put("date",date);
                            hashMap1.put("SenderNumberCart", SenderNumberCart);
                            if (typeTicket[0] == 0) {
                                hashMap1.put("type", "petitDej");
                            } else hashMap1.put("type", "repas");
                            hashMap1.put("nbrTicket", textInputLayout.getEditText().getText().toString());
                            hashMap1.put("CartReceive", s);
                            transaction(hashMap1, s);
                        }
                    });
                    buttonNegative.setOnClickListener(v -> mAlertDialog.dismiss());
                    builder.setView(view);
                    mAlertDialog = builder.create();
                    mAlertDialog.show();
                }
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

    public void transaction(Map<String, String> hashMap, String s) {
        String url = "http://" + LoginActivity.getIpAdd() + "/memoir/server/dont.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response);
                String responsePhp = jsonObject.getString("response1");
                Log.d("response1", "onResponse: "+responsePhp);
                mAlertDialog.dismiss();
                if (responsePhp.equals("ok")) {
                    String st = Objects.equals(hashMap.get("type"), "petitDej") ? "petit deuj" : "repas";
                    new Tools(getContext()).displayAlert("L'opération a reussit", "Vous venez d'envoyer " + s + " " + hashMap.get("nbrTicket") + " " + st);
                } else
                    new Tools(getContext()).displayAlert("L'opération a échoue", "Vous n'avez assez de ticket !");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                return hashMap;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    public Boolean compareTime(String time1, String time2, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
        try {
            Date timeOne = simpleDateFormat.parse(time1);
            Date timeTwo = simpleDateFormat.parse(time2);
            Date CompTime = simpleDateFormat.parse(time);
            assert CompTime != null;
            return CompTime.after(timeOne) && CompTime.before(timeTwo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean compareDateTime(String time1, String time2, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.ENGLISH);
        try {
            Date timeOne = simpleDateFormat.parse(time1);
            Date timeTwo = simpleDateFormat.parse(time2);
            Date CompTime = simpleDateFormat.parse(time);
            assert CompTime != null;
            return CompTime.after(timeOne) && CompTime.before(timeTwo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}