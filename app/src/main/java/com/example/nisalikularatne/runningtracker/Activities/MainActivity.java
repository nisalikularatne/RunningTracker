package com.example.nisalikularatne.runningtracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nisalikularatne.runningtracker.R;

public class MainActivity extends AppCompatActivity {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);

    }
    public void onClickStartNewRun(View view) {
        startActivity(new Intent(this, NewRunActivity.class));
    }
    public void onClickStartRunHistory(View view) {
        startActivity(new Intent(this, HistoryRunActivity.class));
    }


}
