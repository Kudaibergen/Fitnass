package com.akai.fitnass.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.akai.fitnass.db.converters.Converter;
import com.akai.fitnass.db.dao.WorkoutDao;
import com.akai.fitnass.db.domain.Workout;

@Database(entities = Workout.class, version = 1)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WorkoutDao workoutDao();
}
