package com.b1707b.cours.resto_app;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.b1707b.cours.resto_app.reclerview.DetailAdapter;
import com.b1707b.cours.resto_app.reclerview.DetailHolder;
import com.b1707b.cours.resto_app.reclerview.Email;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    private ImageView mImageQrCode;
    private ArrayList<Email> mEmails  = new ArrayList<>();
    DetailAdapter gmailAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mEmails.add(new Email("Boubacar","10-9-2022","Note de L3","Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptates facere explicabo debitis dicta. Tempora ad neque quia mollitia fugit quisquam voluptatem"));
        mEmails.add(new Email("ALpha diallo","18-9-2022","Note de L1","Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptates facere explicabo debitis dicta. Tempora ad neque quia mollitia fugit quisquam voluptatem"));
        mEmails.add(new Email("ALiou","10-3-2022","Note de L3","Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptates facere explicabo debitis dicta. Tempora ad neque quia mollitia fugit quisquam voluptatem"));
        mEmails.add(new Email("Diop","10-3-2022","Note de L3","Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptates facere explicabo debitis dicta. Tempora ad neque quia mollitia fugit quisquam voluptatem"));
        mEmails.add(new Email("Abdou","10-3-2022","Note de L3","Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptates facere explicabo debitis dicta. Tempora ad neque quia mollitia fugit quisquam voluptatem"));
        mEmails.add(new Email("Sara","10-3-2022","Note de L3","Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptates facere explicabo debitis dicta. Tempora ad neque quia mollitia fugit quisquam voluptatem"));
        mEmails.add(new Email("Fatimatou","10-3-2022","Note de L3","Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptates facere explicabo debitis dicta. Tempora ad neque quia mollitia fugit quisquam voluptatem"));
        mEmails.add(new Email("Moustapha","10-3-2022","Note de L3","Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptates facere explicabo debitis dicta. Tempora ad neque quia mollitia fugit quisquam voluptatem"));
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mImageQrCode = view.findViewById(R.id.fragment_home_imgQrCode);
        HomeActivity activity = (HomeActivity) getActivity();
        String numCart = activity.getNumCart();
        generateQR(numCart);
        mRecyclerView = view. findViewById(R.id.fragment_home_riclerView);
        gmailAdapter = new DetailAdapter(mEmails,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(gmailAdapter);
        return view;
    }

    private void generateQR(String numCart) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(numCart, BarcodeFormat.QR_CODE, 150, 150);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            mImageQrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}