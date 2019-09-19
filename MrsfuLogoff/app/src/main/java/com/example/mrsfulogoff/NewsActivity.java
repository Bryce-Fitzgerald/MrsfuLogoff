package com.example.mrsfulogoff;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewsActivity extends AppCompatActivity {
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mContext=this.getBaseContext();
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        initTitleView();
        initBottomView();
    }

    private void initTitleView() {
        new TitleBuilder(this).setTitleText("资讯").setButtonVisible(0).build();
    }

    private void initBottomView() {
        new BottomBuilder(this).setHpButtonClickListener(this).setTrButtonClickListener(this).setClButtonClickListener(this).setUrButtonClickListener(this).build();
    }
}
