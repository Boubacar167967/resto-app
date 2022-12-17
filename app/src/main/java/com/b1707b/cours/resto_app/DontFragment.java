package com.b1707b.cours.resto_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b1707b.cours.resto_app.databinding.FragmentDontBinding;
import com.google.android.material.tabs.TabLayout;

public class DontFragment extends Fragment {
    FragmentDontBinding binding;
    public DontFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDontBinding.inflate(inflater,container,false);
        View view = inflater.inflate(R.layout.fragment_dont, container, false);

        binding.fragmentTabLayout.addTab(binding.fragmentTabLayout.newTab().setText("Par carte"));
        binding.fragmentTabLayout.addTab(binding.fragmentTabLayout.newTab().setText("Scanner"));
        binding.fragmentTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final FragmantAdapterDont adapter = new FragmantAdapterDont(getActivity().getSupportFragmentManager(), binding.fragmentTabLayout.getTabCount());
        binding.fragmentViewPager.setAdapter(adapter);

        binding.fragmentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.fragmentTabLayout));

        binding.fragmentTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //binding.fragmentViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return binding.getRoot();
    }
}