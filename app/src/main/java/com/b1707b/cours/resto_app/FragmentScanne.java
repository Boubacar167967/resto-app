package com.b1707b.cours.resto_app;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b1707b.cours.resto_app.databinding.FragmentScanneBinding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Objects;

public class FragmentScanne extends Fragment {
    FragmentScanneBinding mBinding;
    public FragmentScanne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentScanneBinding.inflate(inflater,container,false);
        scanQR();
        return mBinding.getRoot();
    }
    private void scanQR() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        mLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> mLauncher  = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents()!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}