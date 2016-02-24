package com.mv.dimooon.mvarrior.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.mv.dimooon.mvarrior.PermissionsGuard;
import com.mv.dimooon.mvarrior.R;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by dimooon on 22.02.16.
 */
public class SplashFragment extends BaseFragment{

    private PermissionsGuard permissionsGuard;

    @Override
    protected int getLayoutId() {
        return R.layout.splash_screen_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showNextScreen();
    }

    private void showNextScreen(){
        if(PreferenceManager.getDefaultSharedPreferences(getActivity()).contains("tutorialPassed")){
            MainFragment newFragment = new MainFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_activity_root, newFragment);
            transaction.commit();
        }else {
            TutorialFragment newFragment = new TutorialFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_activity_root, newFragment);
            transaction.commit();
        }
    }
}