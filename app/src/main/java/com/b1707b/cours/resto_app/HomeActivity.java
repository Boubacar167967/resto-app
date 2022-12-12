package com.b1707b.cours.resto_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.b1707b.cours.resto_app.databinding.ActivityHomeBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.achat_tickets:
                    replaceFragment(new TicketFragment());
                    break;
                case R.id.se_regaler:
                    replaceFragment(new EatFragment());
                    break;
                case R.id.dont_tickets:
                    replaceFragment(new DontFragment());
                    break;
            }
            return true;
        });
            MaterialToolbar toolbar = findViewById(R.id.topAppBar);
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.navigation_view);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    drawerLayout.openDrawer(GravityCompat.START);

                }
            });
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                    int id = item.getItemId();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    switch (id)
                    {

                        case R.id.nav_home:
                            Toast.makeText(HomeActivity .this, "Home is Clicked", Toast.LENGTH_SHORT).show();break;
                        case R.id.nav_message:
                            Toast.makeText(HomeActivity .this, "Message is Clicked",Toast.LENGTH_SHORT).show();break;
                        case R.id.synch:
                            Toast.makeText(HomeActivity .this, "Synch is Clicked",Toast.LENGTH_SHORT).show();break;
                        case R.id.trash:
                            Toast.makeText(HomeActivity .this, "Trash is Clicked",Toast.LENGTH_SHORT).show();break;
                        case R.id.settings:
                            Toast.makeText(HomeActivity .this, "Settings is Clicked",Toast.LENGTH_SHORT).show();break;
                        case R.id.nav_login:
                            Toast.makeText(HomeActivity .this, "Login is Clicked",Toast.LENGTH_SHORT).show();break;
                        case R.id.nav_share:
                            Toast.makeText(HomeActivity .this, "Share is clicked",Toast.LENGTH_SHORT).show();break;
                        case R.id.nav_rate:
                            Toast.makeText(HomeActivity .this, "Rate us is Clicked",Toast.LENGTH_SHORT).show();break;
                        default:
                            return true;

                    }
                    return true;
                }
            });


        }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}