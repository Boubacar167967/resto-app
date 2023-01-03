package com.b1707b.cours.resto_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.b1707b.cours.resto_app.favorite.FirebaseDatabase;
import com.b1707b.cours.resto_app.favorite.Menu;
import com.b1707b.cours.resto_app.favorite.Plats;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public  class FragmentDetailMenu extends Fragment{
    SharedPreferences mPreferences;

    private static String ipAdd = LoginActivity.getIpAdd();
    Handler mainHandler = new Handler();
     FragmentDetailMenuBinding binding;
    ProgressDialog progressDialog;
    public FragmentDetailMenu() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailMenuBinding.inflate(inflater,container,false);
        String urlDiner = "http://"+ipAdd+""+"/memoir/res-web/"+getArguments().getString("imgDiner").replace("\\","/").replace("(","").replace(")","");
        String urlDiner1 = "http://"+ipAdd+""+"/memoir/res-web/"+getArguments().getString("imgDiner1").replace("\\","/").replace("(","").replace(")","");
        String urlRepas = "http://"+ipAdd+""+"/memoir/res-web/"+getArguments().getString("imgRepas").replace("\\","/").replace("(","").replace(")","");
        String urlRepas1 = "http://"+ipAdd+""+"/memoir/res-web/"+getArguments().getString("imgRepas1").replace("\\","/").replace("(","").replace(")","");
        Log.d("url_diner",urlDiner);
        new RunFetchImage(new FetchImage(urlDiner,binding.imgDiner1)).start();
        new RunFetchImage(new FetchImage(urlDiner1,binding.imgDiner2)).start();
        new RunFetchImage(new FetchImage(urlRepas,binding.imgRepas1)).start();
        new RunFetchImage(new FetchImage(urlRepas1,binding.imgRepas2)).start();
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = requireActivity().getSharedPreferences("id_plats",Context.MODE_PRIVATE);
        int id_user = sharedPreferences.getInt("id_user",0);
        int id_menu = sharedPreferences1.getInt("id_menu",0);
        int id_repas = sharedPreferences1.getInt("id_repas",0);
        int id_repas1 = sharedPreferences1.getInt("id_repas1",0);
        int id_diner = sharedPreferences1.getInt("id_diner",0);
        int id_diner1 = sharedPreferences1.getInt("id_diner1",0);
        String num_card = sharedPreferences.getString("userNumberCart","");
        //new Favorites(id_user,id_repas,id_menu,binding.fsmFavRepas,getContext()).makeFavorite("Repas");
        Plats diner = new Plats(id_diner);
        Plats diner1 = new Plats(id_diner1);
        Plats repas = new Plats(id_repas);
        Plats repas1 = new Plats(id_repas1);
        Menu menu = new Menu(id_menu,"2023-09-16",diner,diner1,repas,repas1);
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(getContext());
        firebaseDatabase.verifyExistKeyMenu(menu,""+id_menu);
        firebaseDatabase.makeFavorite(binding.fsmFavRepas,num_card,id_menu+"","repas");
        firebaseDatabase.loadNumberFavorite(binding.fsmNbrFavRepas,num_card, String.valueOf(id_menu),"repas");
        firebaseDatabase.makeFavorite(binding.fsmFavRepas1,num_card,id_menu+"","repas1");
        firebaseDatabase.loadNumberFavorite(binding.fsmNbrFavRepas1,num_card, String.valueOf(id_menu),"repas1");
        firebaseDatabase.makeFavorite(binding.fsmFavDiner,num_card,id_menu+"","diner");
        firebaseDatabase.loadNumberFavorite(binding.fsmNbrFavDiner,num_card, String.valueOf(id_menu),"diner");
        firebaseDatabase.makeFavorite(binding.fsmFavDiner1,num_card,id_menu+"","diner1");
        firebaseDatabase.loadNumberFavorite(binding.fsmNbrFavDiner1,num_card, String.valueOf(id_menu),"diner1");
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
        //Log.d("onViewCreatedeeeee", "onViewCreated: "+getResp());

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
                    //progressDialog.show();

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