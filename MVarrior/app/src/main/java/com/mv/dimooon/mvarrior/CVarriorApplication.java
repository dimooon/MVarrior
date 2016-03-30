package com.mv.dimooon.mvarrior;

import android.app.Application;

import com.mv.dimooon.mvarrior.dao.ContactInfo;
import com.mv.dimooon.mvarrior.dao.Experience;
import com.mv.dimooon.mvarrior.dao.Player;
import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by dimooon on 28.03.16.
 */
public class CVarriorApplication extends SugarApp{

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
