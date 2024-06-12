package com.example.comiclibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
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
    public Cursor getAllUsers(){
        Cursor cursor= database.rawQuery("SELECT "+DatabaseHelper.MAIL_UTENTE+", "+DatabaseHelper.PASSWORD_UTENTE+", "+DatabaseHelper.TIPO_UTENTE+
                " FROM "+DatabaseHelper.UTENTI_TABLE,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int updateUser(String emailOld, String emailNew, String password, boolean administrator )
    {
        ContentValues cv= new ContentValues();
        cv.put(DatabaseHelper.MAIL_UTENTE,emailNew);
        cv.put(DatabaseHelper.PASSWORD_UTENTE,password);
        cv.put(DatabaseHelper.TIPO_UTENTE,administrator);
        return database.update(DatabaseHelper.UTENTI_TABLE, cv, DatabaseHelper.MAIL_UTENTE+ "='"+ emailOld+"'", null );
    }
    public boolean deleteUser(String email)
    {
        database.delete(DatabaseHelper.UTENTI_TABLE, DatabaseHelper.MAIL_UTENTE+ "="+ email, null);
        return true;
    }
    public boolean userIsAdmin(String email)
    {
        Cursor cursor = database.rawQuery("SELECT "+DatabaseHelper.TIPO_UTENTE+" FROM "+
                DatabaseHelper.UTENTI_TABLE+" WHERE " +DatabaseHelper.MAIL_UTENTE +" = '" +email+"'",null);
        cursor.moveToFirst();
        int qPermessiUtente = cursor.getColumnIndex(DatabaseHelper.TIPO_UTENTE);
        if(qPermessiUtente>-1){
            return cursor.getInt(qPermessiUtente)>0;
        }
        else return false;
    }
    public String getUser_password(String email)
    {
        Cursor cursor = database.rawQuery("SELECT "+DatabaseHelper.PASSWORD_UTENTE+" FROM "+
                DatabaseHelper.UTENTI_TABLE+" WHERE " +DatabaseHelper.MAIL_UTENTE +" = '" +email+"'",null);
        cursor.moveToFirst();
        int qPermessiUtente = cursor.getColumnIndex(DatabaseHelper.PASSWORD_UTENTE);
        if(qPermessiUtente>-1){
            return cursor.getString(qPermessiUtente);
        }
        else {
            return "";
        }
    }
    public boolean insertNewFumetto(String titolo, String descrizione, Bitmap copertina, int idSerie)
    {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.TITOLO_FUMETTO,titolo);
        cv.put(DatabaseHelper.DESCRIZIONE_FUMETTO,descrizione);
        cv.put(DatabaseHelper.IMMAGINE_COPERTINA,fromBitmapToByte(copertina));
        cv.put(DatabaseHelper.IDS_FUMETTO,idSerie);
        return(database.insert(DatabaseHelper.FUMETTI_TABLE, null, cv)!=-1);
    }
    public int updateFumetto(int id, String titolo, String descrizione, Bitmap copertina, int idSerie)
    {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.TITOLO_FUMETTO,titolo);
        cv.put(DatabaseHelper.DESCRIZIONE_FUMETTO,descrizione);
        cv.put(DatabaseHelper.IMMAGINE_COPERTINA,fromBitmapToByte(copertina));
        cv.put(DatabaseHelper.IDS_FUMETTO,idSerie);
        return database.update(DatabaseHelper.FUMETTI_TABLE, cv, DatabaseHelper.ID_FUMETTO + "="+ id, null );
    }

    public static byte[] fromBitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
    public Cursor ricercaFumetto(String parametro)
    {
        Cursor cursor= database.rawQuery("SELECT F."+DatabaseHelper.IMMAGINE_COPERTINA+", F."+
                        DatabaseHelper.TITOLO_FUMETTO +", S."+
                        DatabaseHelper.TITOLO_SERIE+ ", F."+
                        DatabaseHelper.DISPONIBILITA_FUMETTO+
                        " FROM "+ DatabaseHelper.FUMETTI_TABLE+ " F JOIN "+
                        DatabaseHelper.SERIE_TABLE+ " S ON F."+ DatabaseHelper.IDS_FUMETTO+ " = S."+
                        DatabaseHelper.ID_SERIE+
                        " WHERE F."+ DatabaseHelper.TITOLO_FUMETTO+ " LIKE '%" +parametro +"%'",
                null);
        cursor.moveToFirst();
        return cursor;
    }
}
