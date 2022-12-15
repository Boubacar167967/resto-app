package com.b1707b.cours.resto_app;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginFragment extends Fragment {
    //declaration of the widgets
    private Button mButtonConnect;
    private TextInputEditText mTextInputNumCart;
    private TextInputEditText mTextInputPassword;
    private  final String url = "http://10.106.202.238/server/logApp.php";
    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_login, container, false);
        mButtonConnect = (Button) mainView.findViewById(R.id.fragment_loginBtnConnect);
        mTextInputNumCart = (TextInputEditText)mainView.findViewById(R.id.fragment_loginNumCarte);
        mTextInputPassword = (TextInputEditText) mainView.findViewById(R.id.fragment_loginPassWord);
        mButtonConnect.setOnClickListener(btnClick);
        String s = mTextInputNumCart.getText() +""+ mTextInputPassword.getText();
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
                //Toast.makeText(MainActivity.this, "Vide", Toast.LENGTH_LONG).show();
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
                                //Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intent = new Intent(getContext(),HomeActivity.class);
                                Bundle bundle = new Bundle();
                                jsonObject1 = jsonObject.getJSONObject("content");
                                bundle.putString("name",jsonObject1.getString("name"));
                                bundle.putString("prenom",jsonObject1.getString("prenom"));
                                bundle.putString("numcard",jsonObject1.getString("numcard"));
                                bundle.putString("email",jsonObject1.getString("email"));
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
                }){
                    @Nullable
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