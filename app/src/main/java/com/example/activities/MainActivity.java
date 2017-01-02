package com.example.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.data.DatabaseHandler;
import com.example.data.Route;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

//        Log.d("Insert: ", "Inserting ..");
//        db.addRoute(new Route("Ravi", "2000", "7+"));
//        db.addRoute(new Route("Hugo", "500", "7+"));
//        db.addRoute(new Route("Sepp", "2000", "6+"));
//        db.addRoute(new Route("Martin", "3000", "7+"));


    }

    public void newDBEntry(View view) {
        Intent intent = new Intent(this, NewDBEntryActivity.class);
        startActivity(intent);
    }
}
