package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fleischbank on 30.12.2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "climbingRoutes";

    // Routes table name
    private static final String TABLE_ROUTES = "routes";

    // Routes Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ALTITUDE = "altitude";
    private static final String KEY_LEVEL = "level";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // context.deleteDatabase(DATABASE_NAME);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ROUTES_TABLE = "CREATE TABLE " + TABLE_ROUTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ALTITUDE + " TEXT," + KEY_LEVEL + " TEXT" + ")";
        db.execSQL(CREATE_ROUTES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTES);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new route
    public void addRoute(Route route) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, route.getName()); // Route Name
        values.put(KEY_ALTITUDE, route.getAltitude()); // Route Altitude
        values.put(KEY_LEVEL, route.getLevel()); // Route Level

        // Inserting Row
        db.insert(TABLE_ROUTES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single route
    public Route getRoute(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ROUTES, new String[] { KEY_ID,
                        KEY_NAME, KEY_ALTITUDE, KEY_LEVEL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Route route = new Route(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return route
        return route;
    }

    // Getting All Routes
    public List<Route> getAllRoutes() {
        List<Route> routeList = new ArrayList<Route>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ROUTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Route route = new Route();
                route.setID(Integer.parseInt(cursor.getString(0)));
                route.setName(cursor.getString(1));
                route.setAltitude(cursor.getString(2));
                route.setLevel(cursor.getString(3));
                // Adding route to list
                routeList.add(route);
            } while (cursor.moveToNext());
        }

        // return route list
        return routeList;
    }

    // Updating single route
    public int updateRoute(Route route) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, route.getName());
        values.put(KEY_ALTITUDE, route.getAltitude());
        values.put(KEY_LEVEL, route.getLevel());

        // updating row
        return db.update(TABLE_ROUTES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(route.getID()) });
    }

    // Deleting single route
    public void deleteRoute(Route route) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ROUTES, KEY_ID + " = ?",
                new String[] { String.valueOf(route.getID()) });
        db.close();
    }


    // Getting routes Count
    public int getRoutesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ROUTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
