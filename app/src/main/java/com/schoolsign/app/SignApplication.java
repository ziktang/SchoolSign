package com.schoolsign.app;

import android.app.Application;

import com.schoolsign.utils.FontUtils;
import com.schoolsign.utils.Utils;

/**
 * Created by tctctc on 2017/6/17.
 * Function:
 */

public class SignApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        FreelineCore.init(this,this);
        Utils.init(getApplicationContext());
        FontUtils.init(getApplicationContext());
    }
}
