package com.mv.dimooon.mvarrior;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mv.dimooon.mvarrior.fragments.SettingsFragment;

/**
 * Created by dimooon on 22.02.16.
 */
public class CVBaseActivity extends AppCompatActivity {

    protected PermissionsGuard permissionsGuard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        permissionsGuard = new PermissionsGuard(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsGuard.GrantPermissionListener listener = permissionsGuard.getListener(requestCode);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            listener.onGrant();
        } else {
            listener.onDeclined();
        }
        return;

    }
}
