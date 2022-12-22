package com.b1707b.cours.resto_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmantAdapterDont extends FragmentPagerAdapter {
    int tabCounts;

    public FragmantAdapterDont(@NonNull FragmentManager fm,int tabCounts) {
        super(fm);
        this.tabCounts = tabCounts;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentCarte();
            case 1:
                return new FragmentScanne();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCounts;
    }
}
