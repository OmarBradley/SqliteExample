package com.example.sqliteexample.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by 재화 on 2016-05-08.
 */
public class SqliteExampleApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
