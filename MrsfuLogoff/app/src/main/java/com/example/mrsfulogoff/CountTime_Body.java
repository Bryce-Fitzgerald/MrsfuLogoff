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
    //构造方法设定倒计时及其间隔时间
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
    //重置计时
    public void reset() {
        //如果有计时任务则直接取消
        if (mTimer != null) {
            cancelTimer();
        }
        //将倒计时剩余时间设定等于用户设定的倒计时时间
        mCountDownRemain = mCountDownTime;
        //将计时状态设为完成
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

    //确认计时是否开始
    public boolean isStart() {
        return mCountTime_BaseStatement == CountTime_BaseStatement.START;
    }

    //确认计时是否完成
    public boolean isFinish() {
        return mCountTime_BaseStatement == CountTime_BaseStatement.FINISH;
    }

    //用户设定倒计时时间
    public void setCountDownTime(long countDownTime) {
        this.mCountDownTime = countDownTime;
        this.mCountDownRemain = mCountDownTime;
    }

    //用户设定计时事件间隔时间
    public void setCountDownInterval(long mCountDownInterval) {
        this.mCountDownInterval = mCountDownInterval;
    }

    //放置倒计时监听器
    public void setCountTime_Listener(CountTime_Listener listener) {
        this.mCountTime_Listener = listener;
    }

    //求倒计时剩余时间
    public long getCountDownRemain() {
        return mCountDownRemain;
    }

    //求计时状态
    public CountTime_BaseStatement getCountTime_BaseStatement() {
        return mCountTime_BaseStatement;
    }

    //创建计时器
    protected CountDownTimer createmCountDownTimer(long countDownTime, long countDownInterval) {
        return null;
    }

    //创建计时事务
    protected TimerTask createTimerTask() {
        //抽象类TimerTask()以创建事务
        return new TimerTask() {
            private long startTime = -1;

            @Override
            public void run() {
                //若startTime小于0……
                if (startTime < 0) {
                    //……为第一次回调 记录开始时间，即开始时间=执行至现在时间-（用户设定倒计时-剩余时间）
                    startTime = scheduledExecutionTime() - (mCountDownTime - mCountDownRemain);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //若监听器工作……
                            if (mCountTime_Listener != null) {
                                //……向监听器传入剩余时间
                                mCountTime_Listener.onTick(mCountDownRemain);
                            }
                        }
                    });
                    //若startTime不小于0……
                } else {
                    //……则记录剩余时间，即用户剩余时间=用户设定的倒计时时长-（执行至现在时间-开始计时时间）
                    mCountDownRemain = mCountDownTime - (scheduledExecutionTime() - startTime);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mCountTime_Listener != null) {
                                mCountTime_Listener.onTick(mCountDownRemain);
                            }
                        }
                    });
                    //如果没有剩余时间就停止
                    if (mCountDownRemain <= 0) {
                        stop();
                    }
                }
            }
        };
    }

}
