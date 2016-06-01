package com.example.sqliteexample.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.UnaryOperator;

import lombok.Cleanup;

/**
 * Created by 재화 on 2016-05-08.
 */
public interface SQLiteTemplate {

    public static void executeSQLiteDatabase(Supplier<SQLiteDatabase> beforeUseDatabase, UnaryOperator<SQLiteDatabase> afterUseDatabase) {
        SQLiteDatabase db = beforeUseDatabase.get();
        try {
            db.beginTransaction();
            afterUseDatabase.apply(db).setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("sqlite exception", e.toString());
        } finally {
            db.endTransaction();
        }
    }
}
