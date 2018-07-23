package com.akai.fitnass.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.akai.fitnass.App;
import com.akai.fitnass.R;
import com.akai.fitnass.db.domain.Workout;
import com.akai.fitnass.ui.day.DayActivity;
import com.akai.fitnass.ui.stopwatch.StopWatchActivity;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private DayWorkoutAdapter mAdapter;
    private ArrayList<Workout> days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list_days);
        Button btnNewDay = findViewById(R.id.btn_day);

        days = new ArrayList<>();
        mAdapter = new DayWorkoutAdapter(this, days);
        listView.setAdapter(mAdapter);
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            startActivity(DayActivity.getIntent(MainActivity.this, id));
            return true;
        });

        btnNewDay.setOnClickListener(clickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable = App.getDatabase().workoutDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workouts -> {
                    days.clear();
                    days.addAll(workouts);
                    mAdapter.notifyDataSetChanged();
                });
        compositeDisposable.add(disposable);
    }

    private View.OnClickListener clickListener = v -> startActivity(StopWatchActivity.getIntent(MainActivity.this));
}
