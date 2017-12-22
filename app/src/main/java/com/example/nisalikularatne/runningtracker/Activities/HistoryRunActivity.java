package com.example.nisalikularatne.runningtracker.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nisalikularatne.runningtracker.CustomDataAdapter;
import com.example.nisalikularatne.runningtracker.data.DBHelper;
import com.example.nisalikularatne.runningtracker.R;

/**
 * Created by Nisali Kularatne on 21/12/2017.
 */

public class HistoryRunActivity extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase db;
    ListView recipeList;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runhistory);

        DBHelper dbHelper = new DBHelper(this, null, null, 1);
        db = dbHelper.getWritableDatabase();

        setupView();

    }
public void setupView(){
    Cursor  c = db.rawQuery("select * from RunnerTracker2",null);

    if (c.moveToFirst()) {
        while (!c.isAfterLast()) {
            double distance = c.getDouble(1);
            int id = c.getInt(0);
            String date = c.getString(2);
            String time = c.getString(3);
            ListView lvItems = (ListView) findViewById(R.id.ListView);
// Setup cursor adapter using cursor from last step
            CustomDataAdapter todoAdapter = new CustomDataAdapter(this, c);
// Attach cursor adapter to the ListView
            lvItems.setAdapter(todoAdapter);
            c.moveToNext();
        }
    }
}
}
