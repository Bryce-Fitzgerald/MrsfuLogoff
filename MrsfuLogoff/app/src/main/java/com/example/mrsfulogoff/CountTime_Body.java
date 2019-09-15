package com.example.mrsfulogoff;

import android.os.Handler;
import android.os.CountDownTimer;

import java.util.Timer;
import java.util.TimerTask;

public class CountTime_Body implements CountTime_BaseMethod {
    private Timer mTimer;

    private Handler mHandler;

    //用户设定的倒计时时间
    private long mCountDownTime;
    //计时之间的间隔时间
    private long mCountDownInterval;
    //倒计时剩余时间
    private long mCountDownRemain;

    //创建倒计时监听器的实例
    private CountTime_Listener mCountTime_Listener;
    //创建计时基本状态陈述实例
    private CountTime_BaseStatement mCountTime_BaseStatement = CountTime_BaseStatement.FINISH;

    public CountTime_Body() {
        this.mHandler = new Handler();
    }

    public CountTime_Body(long countDownTime, long countDownInterval) {
        this.setCountDownTime(countDownTime);
        this.setCountDownInterval(countDownInterval);
        this.mHandler = new Handler();
    }

    @Override
    //启动计时
    public void start() {
        //防止重复启动 重新启动要先reset再start
        if (mTimer == null && mCountTime_BaseStatement != CountTime_BaseStatement.START) {
            mTimer = new Timer();

            ///scheduleAtFixedRate(TimerTask task, long delay, long period)
            //Schedules the specified task for repeated fixed-rate execution, beginning after the specified delay.
            mTimer.scheduleAtFixedRate(createTimerTask(), 0, mCountDownInterval);

            //计时状态设定为开始
            mCountTime_BaseStatement = CountTime_BaseStatement.START;
        }
    }

    @Override
    //计时暂停
    public void pause() {
        //若正在运行……
        if (mTimer != null && mCountTime_BaseStatement == CountTime_BaseStatement.START) {
            //……取消计时
            cancelTimer();
            //计时状态设为暂停
            mCountTime_BaseStatement = CountTime_BaseStatement.PAUSE;
        }
    }

    @Override
    //继续计时
    public void resume() {
        //如果计时状态为暂停……
        if (mCountTime_BaseStatement == CountTime_BaseStatement.PAUSE) {
            //……开始计时
            start();
        }
    }

    @Override
    //停止计时
    public void stop() {
        //只要当下有计时任务……
        if (mTimer != null) {
            //……取消计时
            cancelTimer();
            //倒计时剩余时间设为等于用户设定的倒计时时长（重置）
            mCountDownRemain = mCountDownTime;
            //计时状态设为完成
            mCountTime_BaseStatement = CountTime_BaseStatement.FINISH;
            //设定事件交由Handler执行
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //监听器有监听内容则……
                    if (mCountTime_Listener != null) {
                        //……监听器完成任务
                        mCountTime_Listener.onFinish();
                    }
                }
            });
        }
    }

    @Override
    public void reset() {
        if (mTimer != null) {
            cancelTimer();
        }
        mCountDownRemain = mCountDownTime;
        mCountTime_BaseStatement = CountTime_BaseStatement.FINISH;
    }

    //取消计时
    private void cancelTimer() {
        //终止计时
        mTimer.cancel();
        //将被取消的计时任务移出任务队列
        mTimer.purge();
        //无计时
        mTimer = null;
    }

    public boolean isStart() {
        return mCountTime_BaseStatement == CountTime_BaseStatement.START;
    }

    public boolean isFinish() {
        return mCountTime_BaseStatement == CountTime_BaseStatement.FINISH;
    }

    /**
     * @deprecated 使用构造方法
     * @param millisInFuture
     */
    @Deprecated
    public void setCountDownTime(long millisInFuture) {
        this.mCountDownTime = millisInFuture;
        this.mCountDownRemain = mCountDownTime;
    }

    /**
     * @deprecated 使用构造方法
     * @param mCountDownInterval
     */
    @Deprecated
    public void setCountDownInterval(long mCountDownInterval) {
        this.mCountDownInterval = mCountDownInterval;
    }

    public void setCountTime_Listener(CountTime_Listener listener) {
        this.mCountTime_Listener = listener;
    }

    public long getMillisUntilFinished() {
        return mCountDownRemain;
    }

    public CountTime_BaseStatement getCountTime_BaseStatement() {
        return mCountTime_BaseStatement;
    }

    /**
     * @param millisInFuture
     * @param mCountDownInterval
     * @return
     * @deprecated 已更换Timer
     */
    @Deprecated
    protected mCountDownTimer createmCountDownTimer(long millisInFuture, long mCountDownInterval) {
        return null;
    }

    protected TimerTask createTimerTask() {
        //抽象类TimerTask()以创建新任务
        return new TimerTask() {
            private long startTime = -1;

            @Override
            public void run() {
                if (startTime < 0) {
                    //第一次回调 记录开始时间

                    startTime = scheduledExecutionTime() - (mCountDownTime - mCountDownRemain);

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mCountTime_Listener != null) {
                                mCountTime_Listener.onTick(mCountDownRemain);
                            }
                        }
                    });
                } else {
                    //剩余时间
                    mCountDownRemain = mCountDownTime - (scheduledExecutionTime() - startTime);

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mCountTime_Listener != null) {
                                mCountTime_Listener.onTick(mCountDownRemain);
                            }
                        }
                    });
                    if (mCountDownRemain <= 0) {
                        //如果没有剩余时间 就停止
                        stop();
                    }
                }
            }
        };
    }

}
