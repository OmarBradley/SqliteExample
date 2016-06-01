package com.example.sqliteexample.db;

import android.content.ContentValues;

import com.example.sqliteexample.data.Address;

import java.util.List;
import java.util.Map;

/**
 * Created by 재화 on 2016-05-08.
 */
public interface DAO<T extends RowMapper> {
    public long insertData(T data);
    public long updateData(long dataId, ContentValues updateValues);
    public long deleteData(long dataId);
    public List<Address> searchDataColumns(String keyword);

}
