package com.example.comiclibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME="Database_fumetti";
    static final int DATABASE_VERSION=1;
    static final String UTENTI_TABLE="UTENTI";
    static final String MAIL_UTENTE="mail";
    static final String PASSWORD_UTENTE="password";
    static final String TIPO_UTENTE="administrator";
    private static final String CREATE_UTENTI_TABLE_QUERY =
            "CREATE TABLE "+UTENTI_TABLE+"("+MAIL_UTENTE+" VARCHAR(40) PRIMARY KEY NOT NULL, "+ PASSWORD_UTENTE+ " VARCHAR(30) NOT NULL, "
                    + TIPO_UTENTE+ " BOOLEAN NOT NULL DEFAULT 0);" ;


    static final String SERIE_TABLE="SERIE";
    static final String ID_SERIE="id";
    static final String TITOLO_SERIE="titolo";
    static final String DESCRIZIONE_SERIE="descrizione";
    private static final String CREATE_SERIE_TABLE_QUERY =
            "CREATE TABLE "+SERIE_TABLE+"( "+ID_SERIE+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITOLO_SERIE+" VARCHAR(50) NOT NULL, "
                    +DESCRIZIONE_SERIE+" VARCHAR(500));";

    static final String FUMETTI_TABLE="FUMETTI";
    static final String ID_FUMETTO="id";
    static final String TITOLO_FUMETTO="titolo";
    static final String DESCRIZIONE_FUMETTO="descrizione";
    static final String IDS_FUMETTO="idSerie";
    private static final String CREATE_FUMETTI_TABLE_QUERY =
            "CREATE TABLE "+FUMETTI_TABLE+"( "+ID_FUMETTO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITOLO_FUMETTO+" VARCHAR(50) NOT NULL, "
                    +DESCRIZIONE_FUMETTO+" VARCHAR(500), "+IDS_FUMETTO+" INTEGER, FOREIGN KEY ("+IDS_FUMETTO+") REFERENCES "+SERIE_TABLE+"("+ID_SERIE+"));";


    static final String PRESTITI_TABLE="PRESTITI";
    static final String IDU_PRESTITI="idUtente";
    static final String IDF_PRESTITI="idFumetto";
    static final String DATA_PRESTITO="dataPrestito";
    static final String PRESTITO_CONCLUSO="concluso";
    private static final String CREATE_PRESTITI_TABLE_QUERY =
            "CREATE TABLE "+PRESTITI_TABLE+"("+IDU_PRESTITI+" VARCHAR(40) NOT NULL, "+ IDF_PRESTITI+ " INTEGER NOT NULL, "
                    +DATA_PRESTITO+"TEXT NOT NULL, "+PRESTITO_CONCLUSO+"BOOLEAN DEFAULT 0," +
                    "FOREIGN KEY ("+IDU_PRESTITI+") REFERENCES "+UTENTI_TABLE+"("+MAIL_UTENTE+")," +
                    "FOREIGN KEY ("+IDF_PRESTITI+") REFERENCES "+FUMETTI_TABLE+"("+ID_FUMETTO+")," +
                    "PRIMARY KEY ("+IDU_PRESTITI+","+IDF_PRESTITI+"));" ;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_UTENTI_TABLE_QUERY);
        db.execSQL(CREATE_SERIE_TABLE_QUERY);
        db.execSQL(CREATE_FUMETTI_TABLE_QUERY);
        db.execSQL(CREATE_PRESTITI_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ UTENTI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ FUMETTI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ SERIE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ PRESTITI_TABLE);
    }
}
