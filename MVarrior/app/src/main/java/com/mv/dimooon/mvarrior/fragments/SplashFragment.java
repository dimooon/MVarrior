package com.mv.dimooon.mvarrior.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.mv.dimooon.mvarrior.R;

/**
 * Created by dimooon on 22.02.16.
 */
public class SplashFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.splash_screen_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Activity a = getActivity();
                if(a==null){
                    return;
                }

                if(PreferenceManager.getDefaultSharedPreferences(a).contains("tutorialPassed")){
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
        },2000);
    }
}