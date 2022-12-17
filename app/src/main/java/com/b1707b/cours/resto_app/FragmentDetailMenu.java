package com.b1707b.cours.resto_app;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.b1707b.cours.resto_app.databinding.FragmentDetailMenuBinding;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FragmentDetailMenu extends Fragment{
    private static String ipAdd = "10.106.199.59";
    Handler mainHandler = new Handler();
     FragmentDetailMenuBinding binding;
    ProgressDialog progressDialog;
    public FragmentDetailMenu() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailMenuBinding.inflate(inflater,container,false);
        String urlDiner = "http://"+ipAdd+""+"/memoir/mem-web/"+getArguments().getString("imgDiner").replace("\\","/").replace("(","").replace(")","");
        String urlDiner1 = "http://"+ipAdd+""+"/memoir/mem-web/"+getArguments().getString("imgDiner1").replace("\\","/").replace("(","").replace(")","");
        String urlRepas = "http://"+ipAdd+""+"/memoir/mem-web/"+getArguments().getString("imgRepas").replace("\\","/").replace("(","").replace(")","");
        String urlRepas1 = "http://"+ipAdd+""+"/memoir/mem-web/"+getArguments().getString("imgRepas1").replace("\\","/").replace("(","").replace(")","");
        Log.d("url",urlDiner);
        new RunFetchImage(new FetchImage(urlDiner,binding.imgDiner1)).start();
        new RunFetchImage(new FetchImage(urlDiner1,binding.imgDiner2)).start();
        new RunFetchImage(new FetchImage(urlRepas,binding.imgRepas1)).start();
        new RunFetchImage(new FetchImage(urlRepas1,binding.imgRepas2)).start();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fragmentDetailDateMenu.setText(getArguments().getString("dateMenu"));
        binding.fragmentDetailTitleRepas.setText(getArguments().getString("nomRepas"));
        binding.repas1.setText(getArguments().getString("nomRepas1"));
        binding.diner.setText(getArguments().getString("nomDiner"));
        binding.diner1.setText(getArguments().getString("nomDiner1"));

    }
    class RunFetchImage extends Thread{
        FetchImage mFetchImage;

        public RunFetchImage(FetchImage fetchImage) {
            mFetchImage = fetchImage;
        }

        @Override
        public void run() {
            mFetchImage.start();
        }
    }
    //getting the image
    class FetchImage extends Thread{

        String URL;

        Bitmap bitmap;
        ImageView mView;

        FetchImage(String URL){

            this.URL = URL;

        }
        FetchImage(String URL,ImageView view){

            this.URL = URL;
            mView = view;
        }

        @Override
        public void run() {

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("Getting your pic....");
                    progressDialog.setCancelable(true);
                    progressDialog.show();

                }
            });

            InputStream inputStream = null;
            try {
                inputStream = new URL(URL).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    mView.setImageBitmap(bitmap);
                }
            });


        }
    }
}