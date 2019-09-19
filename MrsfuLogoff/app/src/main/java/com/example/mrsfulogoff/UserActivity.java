package com.example.mrsfulogoff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserActivity extends AppCompatActivity {
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mContext=this.getBaseContext();
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        initTitleView();
        initBottomView();
    }

    private void initTitleView() {
        new TitleBuilder(this).setTitleText("我").setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }).build();
    }
    private void initBottomView() {
        new BottomBuilder(this).setHpButtonClickListener(this).setNsButtonClickListener(this).setClButtonClickListener(this).setTrButtonClickListener(this).build();
    }
}


