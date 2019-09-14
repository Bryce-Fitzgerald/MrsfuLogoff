package com.example.mrsfulogoff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        new TitleBuilder(this).setTitleText("首页").setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).build();

    }
}

