package com.example.mrsfulogoff;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleBuilder {

    private View viewTitle;
    private TextView tvTitle;
    private Button btTitle;

    public TitleBuilder(Activity context) {
        viewTitle = context.findViewById(R.id.title_bar);
        tvTitle = (TextView) viewTitle.findViewById(R.id.title_text);
        btTitle=(Button) viewTitle.findViewById(R.id.title_back);
    }

    public TitleBuilder(View context) {
        viewTitle = context.findViewById(R.id.title_bar);
        tvTitle = (TextView) viewTitle.findViewById(R.id.title_text);
        btTitle=(Button) viewTitle.findViewById(R.id.title_back);
    }

    //title
    public TitleBuilder setTitleBgRes(int resId) {
        viewTitle.setBackgroundResource(resId);
        return this;
    }

    public TitleBuilder setTitleText(String text) {
        tvTitle.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        tvTitle.setText(text);
        return this;
    }

    //button
    public TitleBuilder setButtonClickListener(View.OnClickListener listener) {
        if(btTitle.getVisibility()==View.VISIBLE) {
            btTitle.setOnClickListener(listener);
        }
        return  this;
    }

    public View build() {
        return viewTitle;
    }
}

