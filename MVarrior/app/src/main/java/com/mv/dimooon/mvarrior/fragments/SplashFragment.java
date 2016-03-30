package com.mv.dimooon.mvarrior.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.mv.dimooon.mvarrior.PermissionsGuard;
import com.mv.dimooon.mvarrior.R;

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