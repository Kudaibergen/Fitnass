package com.akai.fitnass.db.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Workout {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "laps")
    private ArrayList<String> laps;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getLaps() {
        return laps;
    }

    public void setLaps(ArrayList<String> laps) {
        this.laps = laps;
    }

    @Override
    public String toString() {
        return "id: " + id + ", date: " + date;
    }
}
