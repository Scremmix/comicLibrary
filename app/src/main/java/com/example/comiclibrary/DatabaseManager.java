package com.example.comiclibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DatabaseManager(Context ctx)
    {
        context=ctx;

    }
    public DatabaseManager open() throws SQLDataException {
        dbHelper=new DatabaseHelper(context);
        database=dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        dbHelper.close();
    }
    public boolean insert(String email, String password, boolean administrator)
    {
        ContentValues cv= new ContentValues();
        cv.put(DatabaseHelper.MAIL_UTENTE,email);
        cv.put(DatabaseHelper.PASSWORD_UTENTE,password);
        cv.put(DatabaseHelper.TIPO_UTENTE,administrator);
        return(database.insert(DatabaseHelper.DATABASE_TABLE, null, cv)!=-1);
    }
    public Cursor fetch(){
        String []colonne= new String[]
                {DatabaseHelper.MAIL_UTENTE, DatabaseHelper.PASSWORD_UTENTE, DatabaseHelper.TIPO_UTENTE};
        Cursor cursor= database.query
                (DatabaseHelper.DATABASE_TABLE, colonne, null, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update (String email, String password, boolean administrator )
    {
        ContentValues cv= new ContentValues();
        cv.put(DatabaseHelper.MAIL_UTENTE,email);
        cv.put(DatabaseHelper.PASSWORD_UTENTE,password);
        cv.put(DatabaseHelper.TIPO_UTENTE,administrator);
        int ret = database.update(DatabaseHelper.DATABASE_TABLE, cv, DatabaseHelper.MAIL_UTENTE+ "="+ email, null );
        return ret;
    }
    public boolean delete(String email)
    {
        database.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.MAIL_UTENTE+ "="+ email, null);
        return true;
    }
}
