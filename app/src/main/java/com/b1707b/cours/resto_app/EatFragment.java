package com.b1707b.cours.resto_app;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EatFragment extends Fragment {

    private CardView mViewArgentin;
    private CardView mViewCentrale;
    private CardView mViewSelf;
    private CardView mViewEsp;
    private CardView mViewEnsep;
    private CardView mViewEcoleNormal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eat, container, false);
        mViewArgentin = view.findViewById(R.id.card1);
        mViewCentrale = view.findViewById(R.id.card2);
        mViewSelf = view.findViewById(R.id.card3);
        mViewEsp = view.findViewById(R.id.card4);
        mViewEnsep = view.findViewById(R.id.card5);
        mViewEcoleNormal = view.findViewById(R.id.card6);
        launchMenu(mViewArgentin);
        launchMenu(mViewCentrale);
        launchMenu(mViewEsp);
        launchMenu(mViewEnsep);
        launchMenu(mViewEcoleNormal);
        launchMenu(mViewSelf);
        return view;
    }
    private void launchMenu(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity activity = (HomeActivity) getActivity();
                assert activity != null;
                activity.replaceFragment(new FragmentDetailMenu());
            }
        });
    }
}