package com.mv.dimooon.mvarrior;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by dimooon on 22.02.16.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
