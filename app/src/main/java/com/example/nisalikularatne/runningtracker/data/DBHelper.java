package com.example.nisalikularatne.runningtracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nisalikularatne.runningtracker.RunnerTracker;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RunnerTrackerDB.db";
    public static final String TABLE_RUNNERTRACKER = "RunnerTracker";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DISTANCE = "distance";
    public static final String COLUMN_DATE = "date";

    public DBHelper(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_RUNNERTRACKER_TABLE = "CREATE TABLE " +
                TABLE_RUNNERTRACKER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_DISTANCE
                + " INTEGER," + COLUMN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_RUNNERTRACKER_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUNNERTRACKER);
        onCreate(db);
    }
    public void addRunnerTracker(RunnerTracker runnerTracker) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_DISTANCE, runnerTracker.getRunnerTrackerDistance());
        values.put(COLUMN_DATE, runnerTracker.getRunnerTrackerDate());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_RUNNERTRACKER, null, values);

        db.close();
    }
}