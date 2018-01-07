package com.example.kora.biblioteka_filmow_i_ksiazek;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kora on 2017-12-27.
 */

public class databasehelper  extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME_LIBRARY = "people_table";
    private static final String TABLE_NAME_FILM = "film_table";
    private static final String TABLE_NAME_BOOK = "book_table";

    private static final String COL1_LIBRARY = "ID";
    private static final String COL2_LIBRARY = "name";

    private static final String COL1_FILM = "ID";
    private static final String COL2_FILM = "tytul";
    private static final String COL3_FILM = "rezyser";
    private static final String COL4_FILM = "czas";
    private static final String COL5_FILM = "rok";
    private static final String COL6_FILM = "gatunek";
    private static final String COL7_FILM = "opis";
    private static final String COL8_FILM = "ID_biblioteka";

    private static final String COL1_BOOK = "ID";
    private static final String COL2_BOOK = "tytul1";
    private static final String COL3_BOOK = "autor";
    private static final String COL4_BOOK = "iloscstron";
    private static final String COL5_BOOK = "rok1";
    private static final String COL6_BOOK = "gatunek1";
    private static final String COL7_BOOK = "opis1";
    private static final String COL8_BOOK = "ID_biblioteka";

    public databasehelper(Context context) {
        super(context, TABLE_NAME_LIBRARY, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableLibrary = "CREATE TABLE " + TABLE_NAME_LIBRARY + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2_LIBRARY +" TEXT)";
        String createTableFilm = "CREATE TABLE " + TABLE_NAME_FILM + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2_FILM +" TEXT," +
                COL3_FILM +" TEXT," +
                COL4_FILM +" TEXT," +
                COL5_FILM +" TEXT," +
                COL6_FILM +" TEXT," +
                COL7_FILM +" TEXT," +
                COL8_FILM +" INTEGER)";
        String createTablebook = "CREATE TABLE " + TABLE_NAME_BOOK + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2_BOOK +" TEXT," +
                COL3_BOOK +" TEXT," +
                COL4_BOOK +" TEXT," +
                COL5_BOOK +" TEXT," +
                COL6_BOOK +" TEXT," +
                COL7_BOOK +" TEXT," +
                COL8_BOOK +" INTEGER)";

        db.execSQL(createTableLibrary);
        db.execSQL(createTableFilm);
        db.execSQL(createTablebook);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME_LIBRARY);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME_FILM);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME_BOOK);
        onCreate(db);
    }

    public boolean addDataFilm(String tytul,String rezyser,String czas,String rok,String gatunek,String opis, Integer idbiblioteka) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_FILM, tytul);
        contentValues.put(COL3_FILM, rezyser);
        contentValues.put(COL4_FILM, czas);
        contentValues.put(COL5_FILM, rok);
        contentValues.put(COL6_FILM, gatunek);
        contentValues.put(COL7_FILM, opis);
        contentValues.put(COL8_FILM, idbiblioteka);

        Log.d(TAG, "addDataLibrary: Adding " + tytul + " to " + TABLE_NAME_FILM);

        long result = db.insert(TABLE_NAME_FILM, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean addDatabook(String tytul1,String autor,String iloscstron,String rok1,String gatunek1,String opis1, Integer idbiblioteka) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_BOOK, tytul1);
        contentValues.put(COL3_BOOK, autor);
        contentValues.put(COL4_BOOK, iloscstron);
        contentValues.put(COL5_BOOK, rok1);
        contentValues.put(COL6_BOOK, gatunek1);
        contentValues.put(COL7_BOOK, opis1);
        contentValues.put(COL8_BOOK, idbiblioteka);

        Log.d(TAG, "addDataLibrary: Adding " + tytul1 + " to " + TABLE_NAME_BOOK);

        long result = db.insert(TABLE_NAME_BOOK, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addDataLibrary(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_LIBRARY, item);

        Log.d(TAG, "addDataLibrary: Adding " + item + " to " + TABLE_NAME_LIBRARY);

        long result = db.insert(TABLE_NAME_LIBRARY, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getFilmsFromLibrary(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_FILM  + " WHERE " + COL8_FILM + " = " + id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getbookFromLibrary(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_BOOK  + " WHERE " + COL8_BOOK + " = " + id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getFilm(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_FILM  + " WHERE " + COL1_FILM + " = " + id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getbook(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_BOOK  + " WHERE " + COL1_BOOK + " = " + id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getDataLibrary(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_LIBRARY;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getItemIDLibrary(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1_LIBRARY + " FROM " + TABLE_NAME_LIBRARY +
                " WHERE " + COL2_LIBRARY + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public void updateNameLibrary(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_LIBRARY + " SET " + COL2_LIBRARY +
                " = '" + newName + "' WHERE " + COL1_LIBRARY + " = '" + id + "'" +
                " AND " + COL2_LIBRARY + " = '" + oldName + "'";
        Log.d(TAG, "updateNameLibrary: query: " + query);
        Log.d(TAG, "updateNameLibrary: Setting name to " + newName);
        db.execSQL(query);
    }


    public void deleteNameLibrary(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_LIBRARY + " WHERE "
                + COL1_LIBRARY + " = '" + id + "'" +
                " AND " + COL2_LIBRARY + " = '" + name + "'";
        Log.d(TAG, "deleteNameLibrary: query: " + query);
        Log.d(TAG, "deleteNameLibrary: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}