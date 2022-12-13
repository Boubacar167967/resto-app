package com.b1707b.cours.resto_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import sdk.paytech.sn.PCallback;
import sdk.paytech.sn.PayTech;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketFragment extends Fragment {
    private Button mButton;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TicketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicketFragment newInstance(String param1, String param2) {
        TicketFragment fragment = new TicketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        mButton = view.findViewById(R.id.fragment_ticket_btnLaunch);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click", "click on button");
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
        });
        return view;
    }
}