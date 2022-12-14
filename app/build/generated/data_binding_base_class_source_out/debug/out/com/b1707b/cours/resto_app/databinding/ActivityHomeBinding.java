// Generated by view binder compiler. Do not edit!
package com.b1707b.cours.resto_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.b1707b.cours.resto_app.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final BottomAppBar bottomAppBar;

  @NonNull
  public final BottomNavigationView bottomNavigationView;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final FrameLayout frameLayout;

  @NonNull
  public final AppBarLayout mainTopBar;

  @NonNull
  public final NavigationView navigationView;

  @NonNull
  public final MaterialToolbar topAppBar;

  private ActivityHomeBinding(@NonNull DrawerLayout rootView, @NonNull BottomAppBar bottomAppBar,
      @NonNull BottomNavigationView bottomNavigationView, @NonNull DrawerLayout drawerLayout,
      @NonNull FrameLayout frameLayout, @NonNull AppBarLayout mainTopBar,
      @NonNull NavigationView navigationView, @NonNull MaterialToolbar topAppBar) {
    this.rootView = rootView;
    this.bottomAppBar = bottomAppBar;
    this.bottomNavigationView = bottomNavigationView;
    this.drawerLayout = drawerLayout;
    this.frameLayout = frameLayout;
    this.mainTopBar = mainTopBar;
    this.navigationView = navigationView;
    this.topAppBar = topAppBar;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomAppBar;
      BottomAppBar bottomAppBar = ViewBindings.findChildViewById(rootView, id);
      if (bottomAppBar == null) {
        break missingId;
      }

      id = R.id.bottomNavigationView;
      BottomNavigationView bottomNavigationView = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigationView == null) {
        break missingId;
      }

      DrawerLayout drawerLayout = (DrawerLayout) rootView;

      id = R.id.frame_layout;
      FrameLayout frameLayout = ViewBindings.findChildViewById(rootView, id);
      if (frameLayout == null) {
        break missingId;
      }

      id = R.id.main_top_bar;
      AppBarLayout mainTopBar = ViewBindings.findChildViewById(rootView, id);
      if (mainTopBar == null) {
        break missingId;
      }

      id = R.id.navigation_view;
      NavigationView navigationView = ViewBindings.findChildViewById(rootView, id);
      if (navigationView == null) {
        break missingId;
      }

      id = R.id.topAppBar;
      MaterialToolbar topAppBar = ViewBindings.findChildViewById(rootView, id);
      if (topAppBar == null) {
        break missingId;
      }

      return new ActivityHomeBinding((DrawerLayout) rootView, bottomAppBar, bottomNavigationView,
          drawerLayout, frameLayout, mainTopBar, navigationView, topAppBar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
