package com.jaeger.statusbarutil;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.statusbardemo.R;

/**
 * Created by wangwei on 2018/2/1.
 */

public class OneActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();
    TextView result;
    protected View statusBarView = null;
    protected int statusBarColor = 0;

    private View decodeView;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_one);
        result = (TextView) findViewById(R.id.result);
        decodeView = getWindow().getDecorView();
        transparent19and20();
        if (statusBarColor == 0) {
            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorAccent));
        } else if (statusBarColor != -1) {
            statusBarView = StatusBarCompat.compat(this, statusBarColor);
        }
        transparent19and20();
        readBook();

    }


    private void toggleReadBar() { // 切换工具栏 隐藏/显示 状态
        if (flag) {
            flag = !flag;
            hideReadBar();
        } else {
            flag = !flag;
            showReadBar();
        }
    }


    private synchronized void hideReadBar() {
        hideStatusBar();
        decodeView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);//设置状态栏和Activity的关系
    }

    private synchronized void showReadBar() { // 显示工具栏
        showStatusBar();
        decodeView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void readBook() {
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OneActivity.this, "正在拼命开发中", Toast.LENGTH_SHORT).show();
                toggleReadBar();
            }
        });

    }
}

