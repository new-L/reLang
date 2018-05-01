package com.example.relang;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 14;
    public static final String DATABASE_NAME = "contactDb";
    /*Таблицы*/
    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_COURSE = "course";
    /*Столбцы*/
    /*contacts*/
    public static final String KEY_ID = "_id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    /*course*/
    public static final String KEY_ID_COURSE = "_id";
    public static final String KEY_PROGRESS_ENG = "progress_eng";
    public static final String KEY_PROGRESS_GER = "progress_ger";
    public static final String KEY_EMAIL_ADD = "email_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//вызывается если БД не существует и ее надо создавать

        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID + " integer primary key," + KEY_EMAIL + " text," + KEY_PASSWORD + " text" + ")") ;
        db.execSQL("create table if not exists " + TABLE_COURSE + "(" + KEY_ID_COURSE + " integer primary key autoincrement," + KEY_PROGRESS_ENG + " integer," + KEY_PROGRESS_GER + " integer,"+ KEY_EMAIL_ADD + " text"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_CONTACTS);//метод execSQL позволяет работать с яызеом SQL
        db.execSQL("drop table if exists " + TABLE_COURSE);//метод execSQL позволяет работать с яызеом SQL
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
}
