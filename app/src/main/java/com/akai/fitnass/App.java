package com.akai.fitnass;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.akai.fitnass.db.AppDatabase;

public class App extends Application {
    private static final String DB_NAME = "FITNASS_DATABASE";
    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, DB_NAME).build();
    }

    private static App getInstance() {
        return instance;
    }

    public static AppDatabase getDatabase() {
        return getInstance().database;
    }
}
