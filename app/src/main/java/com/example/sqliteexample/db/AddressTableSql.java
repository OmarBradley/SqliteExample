package com.example.sqliteexample.db;

/**
 * Created by 재화 on 2016-05-08.
 */
public interface AddressTableSql {
    public static final String DB_CREATE = "CREATE TABLE" + AddressTableColumns.TABLE + "("
            + AddressTableColumns._ID + "INTEGER PRIMARY KEY AUTOINCREMENT"
            + AddressTableColumns._NAME + "TEXT NOT NULL"
            + AddressTableColumns._PHONE + "TEXT"
            + AddressTableColumns._HOME + "TEXT"
            + AddressTableColumns._OFFICE + "TEXT);";

    public static final String WHERE_CLAUSE = AddressTableColumns._ID + " =?";
}
