package com.b1707b.cours.resto_app.functions;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.b1707b.cours.resto_app.R;
import com.google.android.material.textfield.TextInputLayout;

public class AlertDialogInput {
    private AlertDialog mAlertDialog;

    public AlertDialogInput(Context context,View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enter your name");
        TextInputLayout textInputLayout = view.findViewById(R.id.amInput);
    }
}
