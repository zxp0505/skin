package com.imesong.themedemo.utils;

import com.imesong.themeplugin.loader.SkinManager;
import com.liulishuo.filedownloader.FileDownloader;

import android.app.Application;

/**
 * Created by ericsong on 16/3/23.
 */
public class StartupUtil {

    public static void initTask(Application application) {
        if (application != null) {
            //换肤模块初始化
            SkinManager.getInstance().init(application.getApplicationContext());
            SkinManager.getInstance().load();

            FileDownloader.init(application);
        } else {
            throw new NullPointerException("StartupUtil initTask application == null");
        }
    }
}

