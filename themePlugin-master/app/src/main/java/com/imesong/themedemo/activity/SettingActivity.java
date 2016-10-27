package com.imesong.themedemo.activity;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.imesong.themedemo.R;
import com.imesong.themedemo.utils.ThemeUtil;
import com.imesong.themeplugin.BaseActivity;
import com.imesong.themeplugin.config.SkinConfig;
import com.imesong.themeplugin.listener.ILoaderListener;
import com.imesong.themeplugin.loader.SkinManager;
import com.imesong.themeplugin.util.L;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.set_default_skin)
    Button setDefaultSkin;
    @Bind(R.id.set_night_skin)
    Button setNightSkin;
    @Bind(R.id.update_online)
    Button updateOnline;
    @Bind(R.id.progressbar)
    NumberProgressBar progressbar;

    private String curSkin;

    private static final String NIGHT_URL = "http://app.2345.com/browser/api/data/NightTheme.skin";
    private static final String GOLD_URL = "http://app.2345.com/browser/api/data/CrazyGold.skin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        curSkin = SkinConfig.getCustomSkinPath(this);
        titleText.setText(curSkin);
        Button bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, WebviewActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.set_default_skin)
    public void onSkinResetClick() {
        SkinManager.getInstance().restoreDefaultTheme();
        titleText.setText("theme_default");
    }

    @OnClick(R.id.set_night_skin)
    public void onSkinSetNightClick() {
        SkinManager.getInstance().load(ThemeUtil.NIGHT_THEME_PATH);
//        File skin = new File(ThemeUtil.NIGHT_THEME_PATH);
//
//        if (skin == null || !skin.exists()) {
//            Toast.makeText(getApplicationContext(), "请检查" + ThemeUtil.NIGHT_THEME_PATH + "是否存在", Toast
//                    .LENGTH_SHORT)
//                    .show();
//            return;
//        }
//        SkinManager.getInstance().load(skin.getAbsolutePath(), skinLoadListener);
    }

    @OnClick(R.id.update_online)
    public void onlineUpdate() {
//                FileDownloader.getImpl().create(GOLD_URL).setPath(ThemeUtil.GOLD_THEME_PATH).setListener(new
//                                                                                                  FileDownloadListener
//                        () {
//                    @Override
//                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                        L.d("syq", "blockComplete");
//                    }
//
//                    @Override
//                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                    }
//
//                    @Override
//                    protected void blockComplete(BaseDownloadTask task) {
//                        L.d("syq", "blockComplete");
//                    }
//
//                    @Override
//                    protected void completed(BaseDownloadTask task) {
//                        SkinManager.getInstance().load(task.getPath(),skinLoadListener);
//                    }
//
//                    @Override
//                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//                    }
//
//                    @Override
//                    protected void error(BaseDownloadTask task, Throwable e) {
//                        L.d("syq", "error");
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    protected void warn(BaseDownloadTask task) {
//                        L.d("syq", "warn");
//                    }
//                }).start();

        SkinManager.getInstance().load(ThemeUtil.GOLD_THEME_PATH);
    }

    private ILoaderListener skinLoadListener = new ILoaderListener() {
        @Override
        public void onStart() {
            L.d("syq", "skinLoadListener onStart");
        }

        @Override
        public void onSuccess() {
            L.d("syq", "skinLoadListener onSuccess");
            curSkin = SkinConfig.getCustomSkinPath(SettingActivity.this);
            titleText.setText(curSkin);
        }

        @Override
        public void onFailed() {
            L.d("syq", "skinLoadListener onFailed");
        }
    };
}
