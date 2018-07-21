package com.akai.fitnass.ui.custom;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.akai.fitnass.R;

import java.util.Locale;

public class MyChronometer extends AppCompatTextView {
    private Handler mHandler = new Handler();
    private long millisTime, startTime, buffer, updateTime = 0L;
    private int minutes, seconds, millis;

    public MyChronometer(Context context) {
        super(context);
    }

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void start() {
        startTime = SystemClock.uptimeMillis();
        mHandler.postDelayed(runnable, 0);
    }

    public void pause() {
        buffer += millisTime;
        mHandler.removeCallbacks(runnable);
    }

    public void reset() {
        clear();
        setText(R.string.reset_value);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisTime = SystemClock.uptimeMillis() - startTime;
            updateTime = buffer + millisTime;
            seconds = (int) updateTime / 1000;
            minutes = seconds / 60;
            seconds = seconds % 60;
            millis = (int) (updateTime % 1000) / 10;
            setText(setFormat(minutes, seconds, millis));
            mHandler.postDelayed(this, 0);
        }
    };

    private String setFormat(int min, int sec, int ms) {
        return String.format(Locale.getDefault(),"%02d:%02d:%02d", min, sec, ms);
    }

    private void clear() {
        millisTime = 0L;
        startTime = 0L;
        buffer = 0L;
        updateTime = 0L;
        minutes = 0;
        seconds = 0;
        millis = 0;
    }
}
