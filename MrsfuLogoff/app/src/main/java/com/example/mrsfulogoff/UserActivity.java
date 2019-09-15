package com.example.mrsfulogoff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mrsfulogoff.MainActivity;
import com.example.mrsfulogoff.R;
import com.example.mrsfulogoff.SelfTestActivity;
import com.example.mrsfulogoff.TitleBuilder;

public class UserActivity extends AppCompatActivity {



private void initView() {
        new TitleBuilder(this).setTitleText("用户信息").setButtonClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        }
        }).build();
        }

public static final String TAG="MainActivity";
@ Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Button startSelfTestActivity = (Button) findViewById(R.id.start_selftest_activity);

        startSelfTestActivity.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent = new Intent(UserActivity.this, SelfTestActivity.class);
        startActivity(intent);
        }
        });
        }}
