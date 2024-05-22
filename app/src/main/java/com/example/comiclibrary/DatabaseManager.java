package com.example.comiclibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    public boolean insertNewUser(String email, String password, boolean administrator)
    {
        ContentValues cv= new ContentValues();
        cv.put(DatabaseHelper.MAIL_UTENTE,email);
        cv.put(DatabaseHelper.PASSWORD_UTENTE,password);
        cv.put(DatabaseHelper.TIPO_UTENTE,administrator);
        return(database.insert(DatabaseHelper.UTENTI_TABLE, null, cv)!=-1);
    }
    public Cursor searchUser(String email)
    {
        String [] colonne= new String[]
            {DatabaseHelper.MAIL_UTENTE, DatabaseHelper.PASSWORD_UTENTE, DatabaseHelper.TIPO_UTENTE};
        Cursor cursor= database.query
                (DatabaseHelper.UTENTI_TABLE, colonne, DatabaseHelper.MAIL_UTENTE + " LIKE " + email,
                        null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor getAllUsers(){
        String [] colonne= new String[]
                {DatabaseHelper.MAIL_UTENTE, DatabaseHelper.PASSWORD_UTENTE, DatabaseHelper.TIPO_UTENTE};
        Cursor cursor= database.query
                (DatabaseHelper.UTENTI_TABLE, colonne, null, null, null, null, DatabaseHelper.MAIL_UTENTE);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int updateUser(String email, String password, boolean administrator )
    {
        ContentValues cv= new ContentValues();
        cv.put(DatabaseHelper.MAIL_UTENTE,email);
        cv.put(DatabaseHelper.PASSWORD_UTENTE,password);
        cv.put(DatabaseHelper.TIPO_UTENTE,administrator);
        return database.update(DatabaseHelper.UTENTI_TABLE, cv, DatabaseHelper.MAIL_UTENTE+ "="+ email, null );
    }
    public boolean deleteUser(String email)
    {
        database.delete(DatabaseHelper.UTENTI_TABLE, DatabaseHelper.MAIL_UTENTE+ "="+ email, null);
        return true;
    }
}
