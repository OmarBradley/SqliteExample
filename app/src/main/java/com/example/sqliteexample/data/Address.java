package com.example.sqliteexample.data;

import android.content.ContentValues;

import com.example.sqliteexample.db.AddressTableColumns;
import com.example.sqliteexample.db.RowMapper;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by 재화 on 2016-05-08.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address implements Serializable, RowMapper {
    private long id;
    public String name;
    public String phone;
    public String home;
    public String office;

    @Override
    public ContentValues mapRow() {
        ContentValues values = new ContentValues();
        values.put(AddressTableColumns._NAME, name);
        values.put(AddressTableColumns._PHONE, phone);
        values.put(AddressTableColumns._HOME, home);
        values.put(AddressTableColumns._OFFICE, office);
        return values;
    }
}
