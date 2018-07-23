package com.akai.fitnass.ui.stopwatch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.akai.fitnass.App;
import com.akai.fitnass.R;
import com.akai.fitnass.db.domain.Workout;
import com.akai.fitnass.ui.custom.MyChronometer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class StopWatchActivity extends AppCompatActivity {
    private MyChronometer mChronometer;
    private Button mReset;
    private Button mLap;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> mElements;

    public static Intent getIntent(Context context) {
        return new Intent(context, StopWatchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLaps();
    }

    private void saveLaps() {
        if (mElements == null) {
            return;
        }

        new Thread(() -> {
            Workout workout = new Workout();
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            workout.setDate(date);
            workout.setLaps(mElements);
            App.getDatabase().workoutDao().insert(workout);
        }).start();
    }

    private View.OnClickListener clickListener = v -> {
        switch (v.getId()) {
            case R.id.btn_start: start();
                break;
            case R.id.btn_pause: pause();
                break;
            case R.id.btn_reset: reset();
                break;
            case R.id.btn_lap: lap();
                break;
        }
    };

    private void lap() {
        String lap = mChronometer.getText().toString();
        mElements.add(lap);
        mAdapter.notifyDataSetChanged();
    }

    private void reset() {
        mChronometer.reset();
        mElements.clear();
        mAdapter.notifyDataSetChanged();
    }

    private void pause() {
        mChronometer.pause();
        mReset.setEnabled(true);
        mLap.setEnabled(false);
    }

    private void start() {
        mLap.setEnabled(true);
        mReset.setEnabled(false);
        mChronometer.start();
    }

    private void init() {
        mChronometer = findViewById(R.id.chronometer);
        Button mStart = findViewById(R.id.btn_start);
        Button mPause = findViewById(R.id.btn_pause);
        mReset = findViewById(R.id.btn_reset);
        mLap = findViewById(R.id.btn_lap);
        ListView mListView = findViewById(R.id.listView);

        mElements = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(StopWatchActivity.this, android.R.layout.simple_list_item_1, mElements);
        mListView.setAdapter(mAdapter);
        mStart.setOnClickListener(clickListener);
        mPause.setOnClickListener(clickListener);
        mReset.setOnClickListener(clickListener);
        mLap.setOnClickListener(clickListener);
    }
}
