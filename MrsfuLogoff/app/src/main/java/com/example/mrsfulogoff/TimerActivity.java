package com.example.mrsfulogoff;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
        TextView tv;
        TextView tv_state;
        EditText mCountDownTime;
        EditText mCountDownInterval;

        private CountTime_Body mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        tv = (TextView) findViewById(R.id.tv);
        tv_state = (TextView) findViewById(R.id.tv_state);
        mCountDownTime = (EditText) findViewById(R.id.mCountDownTime);
        mCountDownInterval = (EditText) findViewById(R.id.mCountDownInterval);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        initTitleView();
    }

    private void initTitleView() {
        new TitleBuilder(this).setTitleText("计时器").setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).build();
    }

        public void clickStart(View v) {
            if (mTimer == null) {
                long countDownTime = Long.parseLong(mCountDownTime.getText().toString());
                long countDownInterval = Long.parseLong(mCountDownInterval.getText().toString());
                mTimer = new CountTime_Body(countDownTime, countDownInterval);
                mTimer.setCountTime_Listener(new CountTime_Listener() {
                    @Override
                    public void onTick(long countDownRemain) {
                        tv.setText(countDownRemain + "ms\n" + countDownRemain / 1000 + "s");
                        Log.d("CountTime_Body", "onTick : " + countDownRemain + "ms");
                    }

                    @Override
                    public void onFinish() {
                        tv.setText("计时已停止");
                        Log.d("CountTime_Body", "onFinish");
                    }
                });
            }
            mTimer.start();
            tv_state.setText(getStateText());
        }

        public void clickPause(View v) {
            if (mTimer != null) {
                mTimer.pause();

                tv_state.setText(getStateText());
            }
        }

        public void clickResume(View v) {
            if (mTimer != null) {
                mTimer.resume();

                tv_state.setText(getStateText());
            }
        }

        public void clickCancel(View v) {
            if (mTimer != null) {
                mTimer.stop();

                tv_state.setText(getStateText());
            }
        }

        public void clickResetStart(View v) {
            if (mTimer != null) {
                mTimer.reset();
                mTimer.start();

                tv_state.setText(getStateText());
            }
        }



        @Override
        protected void onResume() {
            super.onResume();
            if (mTimer != null) {
                mTimer.resume();
                tv_state.setText(getStateText());
            }
        }

        @Override
        protected void onPause() {
            super.onPause();
            if (mTimer != null) {
                mTimer.pause();
                tv_state.setText(getStateText());
            }
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (mTimer != null) {
                mTimer.stop();
                tv_state.setText(getStateText());
            }
        }

        private String getStateText() {
            CountTime_BaseStatement state = mTimer.getCountTime_BaseStatement();
            if (CountTime_BaseStatement.START == state) {
                return "正在倒计时";
            } else if (CountTime_BaseStatement.PAUSE == state) {
                return "倒计时暂停";
            } else if (CountTime_BaseStatement.FINISH == state) {
                return "倒计时未开始";
            }
            return "";
        }

    }



