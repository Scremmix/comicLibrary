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
    static final String IMMAGINE_COPERTINA="copertinaimg";
    static final String IDS_FUMETTO="idSerie";
    static final String DISPONIBILITA_FUMETTO="disponibilita";
    private static final String CREATE_FUMETTI_TABLE_QUERY =
            "CREATE TABLE "+FUMETTI_TABLE+"( "+ID_FUMETTO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITOLO_FUMETTO+" VARCHAR(50) NOT NULL, "
                    +DESCRIZIONE_FUMETTO+" VARCHAR(500), "+ IMMAGINE_COPERTINA+ " BLOB, " +IDS_FUMETTO+" INTEGER, " +
                    DISPONIBILITA_FUMETTO+ " VARCHAR(20), "+
                    "FOREIGN KEY ("+IDS_FUMETTO+") REFERENCES "+SERIE_TABLE+"("+ID_SERIE+"));";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
/*"CREATE TABLE "+FUMETTI_TABLE+"( "+ID_FUMETTO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITOLO_FUMETTO+" VARCHAR(50) NOT NULL, "
            +DESCRIZIONE_FUMETTO+" VARCHAR(500), "+ IMMAGINE_COPERTINA+ " BLOB );";*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_UTENTI_TABLE_QUERY);
        db.execSQL(CREATE_FUMETTI_TABLE_QUERY);
        db.execSQL(CREATE_SERIE_TABLE_QUERY);
        db.execSQL("INSERT INTO "+SERIE_TABLE+ " ("+ TITOLO_SERIE+", "+ DESCRIZIONE_SERIE+ ")" +
                " VALUES('Bluelock', 'Serie manga sul calcio come non lo avete mai visto, la ricerca del fenomeno che porterà il giappone alla vittoria del mondiale')");
        db.execSQL("INSERT INTO "+SERIE_TABLE+ " ("+ TITOLO_SERIE+", "+ DESCRIZIONE_SERIE+ ")" +
                " VALUES('Batman', 'Il vigilante notturno più famoso di tutta Gotham city')");
        db.execSQL("INSERT INTO "+FUMETTI_TABLE+ " ("+ TITOLO_FUMETTO+", "+ DESCRIZIONE_FUMETTO+ ", "+ IMMAGINE_COPERTINA+"," +
                " "+  IDS_FUMETTO  +")" +
                " VALUES('Bluelock 1', 'manga sportivo', NULL, 1 )");
        db.execSQL("INSERT INTO "+FUMETTI_TABLE+ " ("+ TITOLO_FUMETTO+", "+ DESCRIZIONE_FUMETTO+ ", "+ IMMAGINE_COPERTINA+"," +
                " "+  IDS_FUMETTO  +")" +
                " VALUES('Bluelock 2', 'manga sportivo', NULL, 1  )");
        db.execSQL("INSERT INTO "+FUMETTI_TABLE+ " ("+ TITOLO_FUMETTO+", "+ DESCRIZIONE_FUMETTO+ ", "+ IMMAGINE_COPERTINA+"," +
                " "+  IDS_FUMETTO  +")" +
                " VALUES('Bluelock 3', 'manga sportivo', NULL, 1  )");
        db.execSQL("INSERT INTO "+FUMETTI_TABLE+ " ("+ TITOLO_FUMETTO+", "+ DESCRIZIONE_FUMETTO+ ", "+ IMMAGINE_COPERTINA+"," +
                " "+  IDS_FUMETTO  +")" +
                " VALUES('Bluelock 4', 'manga sportivo', NULL, 1  )");
        db.execSQL("INSERT INTO "+FUMETTI_TABLE+ " ("+ TITOLO_FUMETTO+", "+ DESCRIZIONE_FUMETTO+ ", "+ IMMAGINE_COPERTINA+"," +
                " "+  IDS_FUMETTO  +")" +
                " VALUES('Bluelock 5', 'manga sportivo', NULL, 1  )");
        db.execSQL("INSERT INTO "+FUMETTI_TABLE+ " ("+ TITOLO_FUMETTO+", "+ DESCRIZIONE_FUMETTO+ ", "+ IMMAGINE_COPERTINA+"," +
                " "+  IDS_FUMETTO  +")" +
                " VALUES('Batman 95', 'fumetto DC comics', NULL, 2  )");
        db.execSQL("INSERT INTO "+FUMETTI_TABLE+ " ("+ TITOLO_FUMETTO+", "+ DESCRIZIONE_FUMETTO+ ", "+ IMMAGINE_COPERTINA+"," +
                " "+  IDS_FUMETTO  +")" +
                " VALUES('Batman 96', 'fumetto DC comics', NULL, 2 )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ UTENTI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ FUMETTI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ SERIE_TABLE);
    }
}
