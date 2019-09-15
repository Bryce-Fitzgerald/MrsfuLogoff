package com.example.mrsfulogoff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClassActivity extends AppCompatActivity {
    public  static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        mContext=this.getBaseContext();
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        initTitleView();
        initBottomView();

        Button focusTrain=(Button)findViewById(R.id.consult_button);
        focusTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClassActivity.this,ConsultActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initTitleView() {
        new TitleBuilder(this).setTitleText("公开课").setButtonVisible(0).build();
    }

    private void initBottomView() {
        new BottomBuilder(this).setHpButtonClickListener(this).setTrButtonClickListener(this).setNsButtonClickListener(this).setUrButtonClickListener(this).build();
    }
}
