package com.b1707b.cours.resto_app.functions;

import android.app.AlertDialog;
import android.content.Context;

public class Tools {
    private final AlertDialog.Builder mBuilder;

    public Tools(Context context) {
        mBuilder = new AlertDialog.Builder(context);
    }

    public void displayAlert(String title, String message) {
        mBuilder.setTitle(title);
        mBuilder.setMessage(message);
        mBuilder.setPositiveButton("OK", (dialogInterface, i) -> {

        });
        mBuilder.create().show();
    }
}
