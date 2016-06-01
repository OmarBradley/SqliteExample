package com.example.sqliteexample.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.annimon.stream.function.Supplier;
import com.example.sqliteexample.application.SqliteExampleApplication;
import com.example.sqliteexample.data.Address;

import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;

/**
 * Created by 재화 on 2016-05-08.
 */
public class AddressDAOManager extends SQLiteOpenHelper implements DAO<Address> {

    private static final String DB_NAME = "addressBook";
    private static final int DB_VERSION = 1;
    private long result;

    private static final class SingletonHolder {
        private static final AddressDAOManager INSTANCE = new AddressDAOManager();
    }

    public static AddressDAOManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private AddressDAOManager() {
        super(SqliteExampleApplication.getContext(), DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AddressTableSql.DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public long deleteData(long dataId) {
        SQLiteTemplate.executeSQLiteDatabase(this::getWritableDatabase, (db) -> {
            String[] whereArgs = new String[]{"" + dataId};
            result = db.delete(AddressTableColumns.TABLE, AddressTableSql.WHERE_CLAUSE, whereArgs);
            return db;
        });
        return 0;
    }

    @Override
    public long insertData(Address data) {
        SQLiteTemplate.executeSQLiteDatabase(this::getWritableDatabase, (db) -> {
            result = db.insert(AddressTableColumns.TABLE, null, data.mapRow());
            return db;
        });
        return result;
    }

    @Override
    public long updateData(long dataId, ContentValues updateValues) {
        SQLiteTemplate.executeSQLiteDatabase(this::getWritableDatabase, (db) -> {
            String[] whereArgs = new String[]{"" + dataId};
            result = db.update(AddressTableColumns.TABLE, updateValues, AddressTableSql.WHERE_CLAUSE, whereArgs);
            return db;
        });
        return result;
    }

    @Override
    public List<Address> searchDataColumns(String keyword) {
        final List<Address> list = new ArrayList<>();
        SQLiteTemplate.executeSQLiteDatabase(this::getReadableDatabase, (db) -> {
            @Cleanup Cursor cursor = executeQueryAndGetCursor(keyword, db);
            addAddressesInList(cursor, list);
            return db;
        });
        return list;
    }

    private Cursor executeQueryAndGetCursor(String keyword, SQLiteDatabase db) {
        String[] columns = AddressTableColumns.ALL_COLUMNS;
        String selection = setSelection(keyword);
        String[] selectionArgs = setSelectionArgs(keyword);
        String orderBy = AddressTableColumns._NAME + "COLLATE LOCALIZED ASC";
        return db.query(AddressTableColumns.TABLE, columns, selection, selectionArgs, null, null, orderBy);
    }

    private String setSelection(String keyword) {
        if (keyword != null && !keyword.equals("")) {
            return null;
        } else {
            String selection = AddressTableColumns._NAME + " LIKE ? OR " + AddressTableColumns._PHONE + " LIKE ? OR " + AddressTableColumns._HOME + " LIKE ? OR " + AddressTableColumns._OFFICE + " LIKE ?";
            return selection;
        }
    }

    private String[] setSelectionArgs(String keyword) {
        if (keyword != null && !keyword.equals("")) {
            return null;
        } else {
            String[] selectionArgs = new String[]{"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"};
            return selectionArgs;
        }
    }

    private void addAddressesInList(Cursor cursor, List<Address> addressList) {
        while (cursor.moveToNext()) {
            Address address = new Address();
            address.setId(cursor.getLong(cursor.getColumnIndex(AddressTableColumns._ID)));
            address.setName(cursor.getString(cursor.getColumnIndex(AddressTableColumns._NAME)));
            address.setHome(cursor.getString(cursor.getColumnIndex(AddressTableColumns._HOME)));
            address.setPhone(cursor.getString(cursor.getColumnIndex(AddressTableColumns._PHONE)));
            address.setOffice(cursor.getString(cursor.getColumnIndex(AddressTableColumns._OFFICE)));
            addressList.add(address);
        }
    }
}
