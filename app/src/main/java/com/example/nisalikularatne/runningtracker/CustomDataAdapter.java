package com.example.nisalikularatne.runningtracker;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Nisali Kularatne on 21/12/2017.
 */

public class CustomDataAdapter extends CursorAdapter {
    public CustomDataAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listdata, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvBody = (TextView) view.findViewById(R.id.textView5);
        TextView tvPriority = (TextView) view.findViewById(R.id.textView6);
        TextView timeview = (TextView) view.findViewById(R.id.textView7);
        // Extract properties from cursor
        double distance = cursor.getDouble(cursor.getColumnIndexOrThrow("distance"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String time= cursor.getString(cursor.getColumnIndexOrThrow("time"));
        // Populate fields with extracted properties
        tvBody.setText(Double.toString(distance));
        tvPriority.setText(String.valueOf(date));
        timeview.setText(String.valueOf(time));
    }
}
