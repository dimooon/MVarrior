package com.mv.dimooon.mvarrior;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mv.dimooon.mvarrior.fragments.SettingsFragment;
import com.mv.dimooon.mvarrior.fragments.SplashFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

        if (findViewById(R.id.main_activity_root) != null) {

            if (savedInstanceState != null) {
                return;
            }

            SplashFragment firstFragment = new SplashFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.main_activity_root, firstFragment).commit();

        }
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStackImmediate();
            return;
        }
        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStackImmediate();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

            SettingsFragment newFragment = new SettingsFragment();
            getFragmentManager().beginTransaction().replace(R.id.main_activity_root, newFragment).addToBackStack(null).commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}