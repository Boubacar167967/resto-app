package com.b1707b.cours.resto_app.functions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Tools {
    private AlertDialog.Builder mBuilder;

    public Tools(Context context) {
        mBuilder =new  AlertDialog.Builder(context);
    }
    public void displayAlert(String title,String message){
        mBuilder.setTitle(title);
        mBuilder.setMessage(message);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        mBuilder.create().show();
    }
}
