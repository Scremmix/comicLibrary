package com.example.comiclibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME="Database_fumetti";
    static final int DATABASE_VERSION=1;
    static final String DATABASE_TABLE="UTENTI";
    static final String MAIL_UTENTE="mail";
    static final String PASSWORD_UTENTE="password";
    static final String TIPO_UTENTE="administrator";
    private static final String CREATE_DATABASE_QUERY=
            "CREATE TABLE "+DATABASE_TABLE+"("+MAIL_UTENTE+" VARCHAR(40) PRIMARY KEY NOT NULL, "+ PASSWORD_UTENTE+ " VARCHAR(30) NOT NULL, "+ TIPO_UTENTE+ " BOOLEAN NOT NULL DEFAULT 0);" ;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_DATABASE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
    }
}
