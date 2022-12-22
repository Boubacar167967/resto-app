package com.b1707b.cours.resto_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private static String ipAdd;
    public static String getIpAdd(){
        return ipAdd;
    }
    public static void setIpadd(String ip){
        ipAdd = ip;
    }
    private  final String url = "http://"+LoginActivity.getIpAdd()+"/memoir/server/logApp.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setIpadd("10.152.84.9");
        Log.d("iAddresse", "onCreateView: "+url);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Se Connecter"));
        tabLayout.addTab(tabLayout.newTab().setText("Créer un compte"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}