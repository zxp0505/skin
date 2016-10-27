package com.imesong.themedemo.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by ericsong on 16/3/22.
 */
public class ThemeUtil {
    public static final String NIGHT_THEME_NAME = "NightTheme.skin";
    public static final String NIGHT_THEME_PATH = Environment.getExternalStorageDirectory()+ File
            .separator + NIGHT_THEME_NAME;

    public static final String GOLD_THEME_NAME = "CrazyGold.skin";

    public static final String GOLD_THEME_PATH = Environment.getExternalStorageDirectory()+File
            .separator+GOLD_THEME_NAME;

    public static final String THEME_PATH_HOME = Environment.getExternalStorageDirectory().getAbsolutePath();

}
