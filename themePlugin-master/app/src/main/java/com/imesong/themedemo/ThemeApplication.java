package com.imesong.themedemo;

import com.imesong.themedemo.utils.StartupUtil;

import android.app.Application;

/**
 * Created by ericsong on 16/3/22.
 */
public class ThemeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StartupUtil.initTask(this);
    }
}
