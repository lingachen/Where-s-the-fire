package com.example.amy074944.hackson3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rereport);
    }

    public void methodBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void methodSubmit(View view){
        Intent intent = new Intent(this, MainActivity.class);
        EditText text1 = (EditText) findViewById(R.id.longtiedx);
        EditText text2 = (EditText) findViewById(R.id.latiedx);
        EditText text3 = (EditText) findViewById(R.id.azimuthedx);
        EditText text4 = (EditText) findViewById(R.id.fsizeedx);
        intent.putExtra("Longtitude", Double.parseDouble(text1.getText().toString()));
        intent.putExtra("Latitude", Double.parseDouble(text2.getText().toString()));
        intent.putExtra("Azimuth", Double.parseDouble(text3.getText().toString()));
        intent.putExtra("FireSize", Integer.parseInt(text4.getText().toString()));
        Log.v("test", "Back");
        startActivity(intent);
    }
}
