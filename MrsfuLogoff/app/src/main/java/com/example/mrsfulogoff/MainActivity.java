package com.example.mrsfulogoff;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        initTitleView();
        initBottomView();
    }


    private void initTitleView() {
        new TitleBuilder(this).setTitleText("首页").setButtonVisible(0).build();
//        setButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        }).build();

    }
    private void initBottomView(){
        BottomBuilder bottomBuilder=new BottomBuilder();
        new BottomBuilder(this).setTrButtonClickListener(this).build();
    }
}

