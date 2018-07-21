package com.akai.fitnass.ui.day;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.akai.fitnass.App;
import com.akai.fitnass.R;
import com.akai.fitnass.db.domain.Workout;

import java.util.ArrayList;

public class DayActivity extends AppCompatActivity {
    private static final String EXTRA_TAG = "id_workout";

    public static Intent getIntent(Context context, long idWorkout) {
        Intent intent = new Intent(context, DayActivity.class);
        intent.putExtra(EXTRA_TAG, idWorkout);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        long id = getIntent().getLongExtra(EXTRA_TAG, 0L);
        Workout workout = App.getDatabase().workoutDao().getById(id);
        ArrayList<String> laps = (ArrayList<String>) workout.getLaps();

        ListView listView = findViewById(R.id.listView_day_laps);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, laps);
        listView.setAdapter(adapter);
    }
}
