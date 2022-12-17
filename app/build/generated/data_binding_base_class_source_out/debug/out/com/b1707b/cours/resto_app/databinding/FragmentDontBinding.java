// Generated by view binder compiler. Do not edit!
package com.b1707b.cours.resto_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.b1707b.cours.resto_app.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDontBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TabLayout fragmentTabLayout;

  @NonNull
  public final ViewPager fragmentViewPager;

  @NonNull
  public final View view;

  private FragmentDontBinding(@NonNull ConstraintLayout rootView,
      @NonNull TabLayout fragmentTabLayout, @NonNull ViewPager fragmentViewPager,
      @NonNull View view) {
    this.rootView = rootView;
    this.fragmentTabLayout = fragmentTabLayout;
    this.fragmentViewPager = fragmentViewPager;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDontBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDontBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_dont, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDontBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fragmentTabLayout;
      TabLayout fragmentTabLayout = ViewBindings.findChildViewById(rootView, id);
      if (fragmentTabLayout == null) {
        break missingId;
      }

      id = R.id.fragmentViewPager;
      ViewPager fragmentViewPager = ViewBindings.findChildViewById(rootView, id);
      if (fragmentViewPager == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      return new FragmentDontBinding((ConstraintLayout) rootView, fragmentTabLayout,
          fragmentViewPager, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
