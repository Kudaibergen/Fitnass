package com.akai.fitnass.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.akai.fitnass.App;
import com.akai.fitnass.R;
import com.akai.fitnass.db.domain.Workout;
import com.akai.fitnass.ui.day.DayActivity;
import com.akai.fitnass.ui.stopwatch.StopWatchActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DayWorkoutAdapter mAdapter;
    private ArrayList<Workout> days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        Button btnNewDay = findViewById(R.id.btn_day);

        mAdapter = new DayWorkoutAdapter(this, days);
        listView.setAdapter(mAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(DayActivity.getIntent(MainActivity.this, id));
                return true;
            }
        });

        btnNewDay.setOnClickListener(clickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        days = (ArrayList<Workout>) App.getDatabase().workoutDao().getAll();
        mAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(StopWatchActivity.getIntent(MainActivity.this));
        }
    };
}
