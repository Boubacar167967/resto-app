package com.b1707b.cours.resto_app;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.b1707b.cours.resto_app.databinding.FragmentTicketBinding;

import java.util.HashMap;

import sdk.paytech.sn.PCallback;
import sdk.paytech.sn.PayTech;

public class TicketFragment extends Fragment {
 FragmentTicketBinding mBinding;
    int prix = 0;
    int nbrTicket=0;
    public TicketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentTicketBinding.inflate(inflater,container,false);
        String [] type =new String[]{ "Petit dejeuner","Repas"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),R.layout.drawpo,type);
        AutoCompleteTextView autoCompleteTextView=mBinding.autoComplexe ;
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i==0){
                    prix = 50;
                }else
                    prix = 100;
            }
        });
        mBinding.fragmentTicketBtnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                String s= mBinding.ftNbrTicket.getText().toString();
                nbrTicket = Integer.parseInt(s);

                builder.setMessage(nbrTicket+" tickets vous Coûte: "+prix*nbrTicket+" FCFA")
                        .setCancelable(false)
                        .setPositiveButton("continuer", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HashMap params = new HashMap<>();
                                params.put("item_id", 2);

                                new PayTech((AppCompatActivity) getActivity()).setRequestTokenUrl("https://sample.paytech.sn/paiement.php").setParams(params).setPresentationMode(PayTech.FLOATING_VIEW) // optional default to FULL_SCREEN
                                        .setFloatTopMargin(25) // optional default to 25
                                        .setLoadingDialogText("Chargement") //optional Chargement
                                        .setCallback(new PCallback() {
                                            @Override
                                            public void onResult(Result result) {
                                                if (result == Result.SUCCESS) {
                                                    Toast.makeText(getActivity(), "Paiement Effectuer", Toast.LENGTH_SHORT).show();
                                                } else if (result == Result.CANCEL) {
                                                    Toast.makeText(getActivity(), "Vous avez annulez le paiement", Toast.LENGTH_SHORT).show();
                                                } else if (result == Result.ERROR) {
                                                    Toast.makeText(getActivity(), "Erreur lors du paiement", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }).send();
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return mBinding.getRoot();
    }
}