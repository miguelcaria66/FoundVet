package com.example.android.foundvet.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dfilipep on 19/07/2017.
 */

public class LoginDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 5;

    public LoginDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " +
                LoginContract.LoginEntry.TABLE_NAME + " (" +
                LoginContract.LoginEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LoginContract.LoginEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                LoginContract.LoginEntry.COLUMN_PASSOWORD + " TEXT NOT NULL, " +
                LoginContract.LoginEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                LoginContract.LoginEntry.COLUMN_ADDRESS + " TEXT NOT NULL, " +
                LoginContract.LoginEntry.COLUMN_PHONE + " TEXT CHECK(" + LoginContract.LoginEntry.COLUMN_PHONE + " IS NOT NULL OR LENGTH(" + LoginContract.LoginEntry.COLUMN_PHONE + ") < 10));";

        db.execSQL(SQL_CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LoginContract.LoginEntry.TABLE_NAME);
        onCreate(db);
    }
}
