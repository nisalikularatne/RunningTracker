package com.example.nisalikularatne.runningtracker.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nisalikularatne.runningtracker.CustomDataAdapter;
import com.example.nisalikularatne.runningtracker.R;
import com.example.nisalikularatne.runningtracker.data.DBHelper;

/**
 * Created by Nisali Kularatne on 22/12/2017.
 */

public class LogsForEachDay extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase db;
    private TextView textView2;
    private TextView maxDistance;

    private TextView distancedateview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_by_day);
        DBHelper dbHelper = new DBHelper(this, null, null, 1);
        db = dbHelper.getWritableDatabase();
        textView2 = (TextView) findViewById(R.id.textView2);
        maxDistance = (TextView) findViewById(R.id.maxDistance);
        distancedateview = (TextView) findViewById(R.id.distancedateview);
        setupView();
        setupView1();
    }

    public void setupView() {
        Cursor c = db.rawQuery("select date, sum(distance) from RunnerTracker2 group by date", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                String date = c.getString(0);
                double distance = c.getDouble(1);
                textView2.append("Date:\t"+ date+" \n"+"Distanced jogged:\t "+Double.toString(distance)+"m"+"\n\n\n");

                c.moveToNext();
            }
        }
    }

    public void setupView1() {
        Cursor c = db.rawQuery("SELECT max(a) FROM (SELECT sum(distance) as a FROM RunnerTracker2 GROUP BY date) as maxd ;", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {

                double distance = c.getDouble(0);
                maxDistance.append("Maximum Distance Jogged in a day:\t "+Double.toString(distance)+"m"+"\n\n");


                c.moveToNext();
            }
        }

    }
}

