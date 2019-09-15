package com.example.mrsfulogoff;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.startActivity;

public class BottomBuilder  {
    private View viewBottom;
    private Button hpBottom;
    private Button trBottom;
    private Button nsBottom;
    private  Button clBottom;
    private Button urBottom;
    private Context mContext;
    public BottomBuilder()
    {

    }

    public BottomBuilder(Activity context) {
        viewBottom=context.findViewById(R.id.bottom_bar);
        hpBottom=(Button)viewBottom.findViewById(R.id.bottom_homepage);
        trBottom=(Button)viewBottom.findViewById(R.id.bottom_train);
        nsBottom=(Button)viewBottom.findViewById(R.id.bottom_news);
        clBottom=(Button)viewBottom.findViewById(R.id.bottom_class);
        urBottom=(Button)viewBottom.findViewById(R.id.bottom_user);
    }
    public BottomBuilder(View context) {
        viewBottom=context.findViewById(R.id.bottom_bar);
        hpBottom=(Button)viewBottom.findViewById(R.id.bottom_homepage);
        trBottom=(Button)viewBottom.findViewById(R.id.bottom_train);
        nsBottom=(Button)viewBottom.findViewById(R.id.bottom_news);
        clBottom=(Button)viewBottom.findViewById(R.id.bottom_class);
        urBottom=(Button)viewBottom.findViewById(R.id.bottom_user);
    }
    //first bottom of homepage
    public BottomBuilder setHpButtonClickListener(final Context context) {
        hpBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext=context;
              //  Toast.makeText(mContext,"test",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                mContext.startActivity(intent);
            }
        });
        return  this;
    }
    //second bottom of train
    public BottomBuilder setTrButtonClickListener(final Context context) {
        trBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext=context;
                //  Toast.makeText(mContext,"test",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,TrainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                mContext.startActivity(intent);
            }
        });
        return  this;
    }
    //third bottom of news
    public BottomBuilder setNsButtonClickListener(View.OnClickListener listener) {
        nsBottom.setOnClickListener(listener);
        return  this;
    }
    //forth bottom of class
    public BottomBuilder setClButtonClickListener(View.OnClickListener listener) {
        clBottom.setOnClickListener(listener);
        return  this;
    }
    //fifth bottom of user
    public BottomBuilder setUrButtonClickListener(View.OnClickListener listener) {
        urBottom.setOnClickListener(listener);
        return  this;
    }



    public View build() {
        return viewBottom;
    }
 }

