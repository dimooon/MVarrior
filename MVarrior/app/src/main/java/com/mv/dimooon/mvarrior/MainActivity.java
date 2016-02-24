package com.mv.dimooon.mvarrior;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mv.dimooon.mvarrior.fragments.SplashFragment;

public class MainActivity extends CVBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

        if (findViewById(R.id.main_activity_root) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }

        initiateSplashScreen();
    }

    private void initiateSplashScreen(){

        SplashFragment splashFragment = new SplashFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_activity_root, splashFragment).commit();
    }
}