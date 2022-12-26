package com.b1707b.cours.resto_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.Objects;

public class LoginFragment extends Fragment {
    //declaration of the widgets
    private Button mButtonConnect;
    private TextInputEditText mTextInputNumCart;
    private TextInputEditText mTextInputPassword;
    //share it is for sending the data over the application
    SharedPreferences mSharedPreferences ;
    private  final String url = "http://"+LoginActivity.getIpAdd()+"/memoir/server/logApp.php";

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSharedPreferences = requireActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        View mainView = inflater.inflate(R.layout.fragment_login, container, false);
        mButtonConnect = (Button) mainView.findViewById(R.id.fragment_loginBtnConnect);
        mTextInputNumCart = (TextInputEditText)mainView.findViewById(R.id.fragment_loginNumCarte);
        mTextInputPassword = (TextInputEditText) mainView.findViewById(R.id.fragment_loginPassWord);
        mButtonConnect.setOnClickListener(btnClick);
        String s = mTextInputNumCart.getText() +""+ mTextInputPassword.getText();
        Log.d("iAddresse", "onCreateView: "+url);
        return mainView;
    }
    //define the button for connection
    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            String string_NumCard_or_email =(String) mTextInputNumCart.getText().toString();
            String string_Password = (String) mTextInputPassword.getText().toString();

            if(string_Password.equals(""))
            {
                new Tools(getContext()).displayAlert("erreur","Remplissez les champs !");
            }else{
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        JSONObject jsonObject1  =null;
                        try {
                            jsonObject = new JSONObject(response);
                            String message =  jsonObject.getString("message");
                            if (message.equals("message_error")){
                                new Tools(getActivity()).displayAlert("erreur","Verifiez vos informations");
                            }
                            else {
                                SharedPreferences.Editor editor = mSharedPreferences.edit();
                                Intent intent = new Intent(getContext(),HomeActivity.class);
                                Bundle bundle = new Bundle();
                                jsonObject1 = jsonObject.getJSONObject("content");
                                bundle.putString("name",jsonObject1.getString("name"));
                                bundle.putString("prenom",jsonObject1.getString("prenom"));
                                bundle.putString("numcard",jsonObject1.getString("numcard"));
                                bundle.putString("email",jsonObject1.getString("email"));
                                intent.putExtras(bundle);
                                editor.putInt("id_user",jsonObject1.getInt("id"));
                                editor.putString("userNumberCart",jsonObject1.getString("numcard"));
                                editor.putString("nbr_repas",jsonObject1.getString("nbr_repas"));
                                editor.putString("nbr_pet_deuj",jsonObject1.getString("nbr_pet_deuj"));
                                editor.apply();
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show()){
                    @NonNull
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> hashMap = new HashMap<>();
                        hashMap.put("numCard",string_NumCard_or_email);
                        hashMap.put("passwords",string_Password);
                        return hashMap;
                    }
                };
                MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
            }
        }
    };
}