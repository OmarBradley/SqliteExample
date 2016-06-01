package com.example.sqliteexample.db;

import android.provider.BaseColumns;

/**
 * Created by 재화 on 2016-05-08.
 */
public interface AddressTableColumns extends BaseColumns {
    public static final String TABLE = "addressTable";
    public static final String _NAME = "name";
    public static final String _PHONE = "phone";
    public static final String _HOME = "home";
    public static final String _OFFICE = "office";

    public static final String[] ALL_COLUMNS = new String[]{AddressTableColumns._ID, AddressTableColumns._NAME, AddressTableColumns._PHONE, AddressTableColumns._HOME, AddressTableColumns._OFFICE};

}
