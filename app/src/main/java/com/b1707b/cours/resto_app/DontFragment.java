package com.b1707b.cours.resto_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b1707b.cours.resto_app.databinding.FragmentDontBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class DontFragment extends Fragment {
    FragmentDontBinding binding;

    public DontFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDontBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}