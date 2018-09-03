package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.pets.data.PetContract.*;

//must implement onCreate() and onUpgrade()
//must write DATABASE_VERSION, DATABASE_NAME, SQL_CREATE_ENTRIES, and SQL_DELETE_ENTRIES
public class PetDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shelter.db";

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ENTRIES = "CREATE TABLE " + petsEntry.TABLE_NAME + " (" +
                        PetContract.petsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        petsEntry.COLUMN_PET_NAME + " TEXT NOT NULL," +
                        petsEntry.COLUMN_PET_BREED + " TEXT," +
                        petsEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL," +
                        petsEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy
        // to simply to discard the data and start over
        final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + petsEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
