package com.dzk.hotfix.app;

import android.app.Application;

import com.dzk.hotfix.EnjoyFix;

import java.io.File;

/**
 * @author jackie
 * @date 2020/12/5
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EnjoyFix.installDex(this,new File("sdcard/patch.dex"));
    }
}
