package com.example.mrsfulogoff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrainActivity extends AppCompatActivity {
  public static MainActivity mactivity;
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        //mactivity=(MainActivity) this;
        mContext = this.getBaseContext();
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        initTitleView();
        initBottomView();

        Button focusTrain=(Button)findViewById(R.id.focus_button);
        focusTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrainActivity.this,TimerActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initTitleView() {
        new TitleBuilder(this).setTitleText("呼吸训练").setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }).build();
    }
    private void initBottomView() {
        new BottomBuilder(this).setHpButtonClickListener(this).build();
    }
}
