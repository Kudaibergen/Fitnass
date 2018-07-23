package com.akai.fitnass.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.akai.fitnass.db.domain.Workout;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface WorkoutDao {

    @Query("SELECT * FROM workout")
    Flowable<List<Workout>> getAll();

    @Query("SELECT * FROM workout WHERE id = :id")
    Single<Workout> getById(long id);

    @Insert
    void insert(Workout workout);

    @Update
    void update(Workout workout);

    @Delete
    void delete(Workout workout);
}
