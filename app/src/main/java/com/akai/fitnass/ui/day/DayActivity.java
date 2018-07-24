package com.akai.fitnass.ui.day;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.akai.fitnass.App;
import com.akai.fitnass.R;
import com.akai.fitnass.db.domain.Workout;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DayActivity extends AppCompatActivity {
    private static final String EXTRA_TAG = "id_workout";
    private WorkoutAdapter adapter;
    private ArrayList<String> laps;

    public static Intent getIntent(Context context, long idWorkout) {
        Intent intent = new Intent(context, DayActivity.class);
        intent.putExtra(EXTRA_TAG, idWorkout);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        laps = new ArrayList<>();

        final long id = getIntent().getLongExtra(EXTRA_TAG, 0L);

        ListView listView = findViewById(R.id.listView_day_laps);
        adapter = new WorkoutAdapter(this, laps);
        listView.setAdapter(adapter);

        App.getDatabase().workoutDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Workout>() {
                    @Override
                    public void onSuccess(Workout workout) {
                        laps.clear();
                        laps.addAll(workout.getLaps());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "error: " + e.getMessage());
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}
