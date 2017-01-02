package com.example.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.data.DatabaseHandler;
import com.example.data.Route;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.example.activities.R.id.txtRouteLevel;
import static com.example.activities.R.id.txtRouteName;

public class NewDBEntryActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private Button saveButton;
    private EditText txtRouteName;
    private EditText txtRouteAltitude;
    private EditText txtRouteLevel;
    private ListView listView;

    private ArrayAdapter adapter;
    private ArrayList<String> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dbentry);

        db = new DatabaseHandler(this);
        saveButton = (Button)findViewById(R.id.saveButton);
        txtRouteName = (EditText)findViewById(R.id.txtRouteName);
        txtRouteAltitude = (EditText)findViewById(R.id.txtRouteAltitude);
        txtRouteLevel = (EditText)findViewById(R.id.txtRouteLevel);
        listView = (ListView)findViewById(R.id.db_listview);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveToDB();
            }
        });
        List<Route> routes = db.getAllRoutes();
        dbList = new ArrayList<String>();

        for (Route cn : routes) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Altitude: " + cn.getAltitude() + " ,Level: " + cn.getLevel();
            dbList.add(log);
            Log.d("Name: ", log);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dbList);
        listView.setAdapter(adapter);

    }

    private void saveToDB() {
        db.addRoute(new Route(txtRouteName.getText().toString(), txtRouteAltitude.getText().toString(), txtRouteLevel.getText().toString()));
        Log.d("DB: " ,"Route saved successfully " + txtRouteName.getText().toString());
        updateListView();
    }

    private void updateListView() {
        List<Route> routes = db.getAllRoutes();
        dbList.clear();
        for (Route cn : routes) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Altitude: " + cn.getAltitude() + " ,Level: " + cn.getLevel();
            dbList.add(log);
        }
        adapter.notifyDataSetChanged();
    }
}
