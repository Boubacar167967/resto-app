package com.b1707b.cours.resto_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
}