// Generated by view binder compiler. Do not edit!
package com.b1707b.cours.resto_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.b1707b.cours.resto_app.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button fragmentRegisterBtnValid;

  @NonNull
  public final TextInputEditText fragmentRegisterEmailTxt;

  @NonNull
  public final TextInputEditText fragmentRegisterLastNameTxt;

  @NonNull
  public final TextInputEditText fragmentRegisterNameTxt;

  @NonNull
  public final TextInputEditText fragmentRegisterNumCartTxt;

  @NonNull
  public final TextInputEditText fragmentRegisterPasswordTxt;

  @NonNull
  public final ScrollView scrollView2;

  private FragmentRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button fragmentRegisterBtnValid, @NonNull TextInputEditText fragmentRegisterEmailTxt,
      @NonNull TextInputEditText fragmentRegisterLastNameTxt,
      @NonNull TextInputEditText fragmentRegisterNameTxt,
      @NonNull TextInputEditText fragmentRegisterNumCartTxt,
      @NonNull TextInputEditText fragmentRegisterPasswordTxt, @NonNull ScrollView scrollView2) {
    this.rootView = rootView;
    this.fragmentRegisterBtnValid = fragmentRegisterBtnValid;
    this.fragmentRegisterEmailTxt = fragmentRegisterEmailTxt;
    this.fragmentRegisterLastNameTxt = fragmentRegisterLastNameTxt;
    this.fragmentRegisterNameTxt = fragmentRegisterNameTxt;
    this.fragmentRegisterNumCartTxt = fragmentRegisterNumCartTxt;
    this.fragmentRegisterPasswordTxt = fragmentRegisterPasswordTxt;
    this.scrollView2 = scrollView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fragment_registerBtnValid;
      Button fragmentRegisterBtnValid = ViewBindings.findChildViewById(rootView, id);
      if (fragmentRegisterBtnValid == null) {
        break missingId;
      }

      id = R.id.fragment_registerEmail_txt;
      TextInputEditText fragmentRegisterEmailTxt = ViewBindings.findChildViewById(rootView, id);
      if (fragmentRegisterEmailTxt == null) {
        break missingId;
      }

      id = R.id.fragment_registerLastName_txt;
      TextInputEditText fragmentRegisterLastNameTxt = ViewBindings.findChildViewById(rootView, id);
      if (fragmentRegisterLastNameTxt == null) {
        break missingId;
      }

      id = R.id.fragment_registerName_txt;
      TextInputEditText fragmentRegisterNameTxt = ViewBindings.findChildViewById(rootView, id);
      if (fragmentRegisterNameTxt == null) {
        break missingId;
      }

      id = R.id.fragment_registerNumCart_txt;
      TextInputEditText fragmentRegisterNumCartTxt = ViewBindings.findChildViewById(rootView, id);
      if (fragmentRegisterNumCartTxt == null) {
        break missingId;
      }

      id = R.id.fragment_registerPassword_txt;
      TextInputEditText fragmentRegisterPasswordTxt = ViewBindings.findChildViewById(rootView, id);
      if (fragmentRegisterPasswordTxt == null) {
        break missingId;
      }

      id = R.id.scrollView2;
      ScrollView scrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView2 == null) {
        break missingId;
      }

      return new FragmentRegisterBinding((ConstraintLayout) rootView, fragmentRegisterBtnValid,
          fragmentRegisterEmailTxt, fragmentRegisterLastNameTxt, fragmentRegisterNameTxt,
          fragmentRegisterNumCartTxt, fragmentRegisterPasswordTxt, scrollView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
