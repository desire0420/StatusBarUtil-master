package com.jaeger.statusbarutil;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.jaeger.statusbardemo.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;


/**
 * Created by lxp on 2015/12/29.
 * 整个app的Activity基类
 */
public abstract class GentouBaseActivity extends FragmentActivity {


    private Boolean isRunning = false;

    /**
     * -初始化控件
     */
    protected abstract void findViews();

    /**
     * -设置控件监听器
     */
    protected abstract void setListeners();

    /**
     * -初始化数据
     */
    protected abstract void initData();

    /**
     * -初始化控件数据
     */
    protected abstract void initViews();

    /*
     * 标示activity的唯一值
     * 在网络请求发起请求与取消请求，需要用到此value；
     */
    protected abstract String getName();




    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 19) {
            setTheme(R.style.style_tint_status_bar);
        }
        super.onCreate(savedInstanceState);
        initSystemBar(R.color.colorAccent);
    }

    private void initSystemBar(int color) {
        // Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT
        if (Build.VERSION.SDK_INT >= 19) {
            setTranslucentStatus(this, true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setStatusBarTintResource(color);
        }
    }

    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
