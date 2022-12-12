package com.b1707b.cours.resto_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.b1707b.cours.resto_app.functions.Tools;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {
    private Button mButtonValid ;
    private TextInputEditText mName;
    private TextInputEditText mLastName;
    private TextInputEditText mNumCard;
    private TextInputEditText mPassword;
    private TextInputEditText mEmail;
    private final String url = "http://192.168.137.225/server/registerApp.php";
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_register, container, false);
        mButtonValid = (Button)mainView.findViewById(R.id.fragment_registerBtnValid);
        mName = (TextInputEditText)mainView.findViewById(R.id.fragment_registerName_txt);
        mLastName = (TextInputEditText)mainView.findViewById(R.id.fragment_registerLastName_txt);
        mEmail = (TextInputEditText)mainView.findViewById(R.id.fragment_registerEmail_txt);
        mPassword = (TextInputEditText)mainView.findViewById(R.id.fragment_registerPassword_txt);
        mNumCard = (TextInputEditText)mainView.findViewById(R.id.fragment_registerNumCart_txt);
        mButtonValid.setOnClickListener(btnRegister);
        return mainView;
    }
    private View.OnClickListener btnRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String string_Name = mName.getText().toString();
            String string_lastName = mLastName.getText().toString();
            String string_cardNum = mNumCard.getText().toString();
            String string_email = mEmail.getText().toString();
            String string_password = mPassword.getText().toString();
            if (string_Name.isEmpty() || string_lastName.isEmpty() || string_cardNum.isEmpty() || string_email.isEmpty() || string_password.isEmpty()) {
                //Toast.makeText(RegisterActivity.this, "Aucun champ ne doit etre null", Toast.LENGTH_SHORT).show();
                new Tools(getActivity()).displayAlert("erreur", "Vérifiez que toutes champs son bien remplit !");
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            //Toast.makeText(RegisterActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            //new Tools(RegisterActivity.this).displayAlert("Alert",jsonObject.getString("info"));
                            if(jsonObject.getString("info").equals("success")){
                                Intent intent = new Intent(getContext(),HomeActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("name",string_Name);
                                bundle.putString("prenom",string_lastName);
                                bundle.putString("numcard",string_cardNum);
                                bundle.putString("email",string_email);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
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
                        hashMap.put("nom", string_Name);
                        hashMap.put("prenom", string_lastName);
                        hashMap.put("numcard", string_cardNum);
                        hashMap.put("email", string_email);
                        hashMap.put("passwords", string_password);
                        return hashMap;
                    }
                };
                MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
            }
        }
    };
}