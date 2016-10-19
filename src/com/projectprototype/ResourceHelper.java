package com.projectprototype;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.projectprototype.lib.WeekViewEvent;

public class ResourceHelper extends SQLiteOpenHelper{
    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "ResourceDB";
    private static final String table_RESOURCES = "Resources";
    private static final String leave_ID = "id";
    private static final String resource_EID = "resourceEid";
    private static final String resource_NAME = "resourceName";
    private static final String resource_TOWER = "resourceTower";
    private static final String resource_STATUS = "resourceStatus";

    public ResourceHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String CREATE_BOOK_TABLE = "CREATE TABLE Resources ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "resourceEid TEXT, " + "resourceName TEXT, " + "resourceTower TEXT, " + "resourceStatus TEXT )";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Leaves");
        this.onCreate(db);
    }

    public boolean createResource(String resourceEID, String resourceName, String resourceTower, String resourceStatus) {
        // get reference of the BookDB database



        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(resource_EID, resourceEID);
        values.put(resource_NAME, resourceName);
        values.put(resource_TOWER, resourceTower);
        values.put(resource_STATUS, resourceStatus);

        // insert book
        long result = db.insert(table_RESOURCES, null, values);

        // close database transaction
        db.close();

        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_RESOURCES,null,null);
        db.close();
    }


    public List<String> getAllResource() {
        List<String> output = new ArrayList<String>();
        Integer id;
        String resourceName;

        //Log.i("myApp", date);
        // select book query
        //String query = "SELECT  * FROM " + table_LEAVES;
        String query = "SELECT  resourceName FROM Resources";

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        if (cursor.moveToFirst()) {
            do {
                id = Integer.parseInt(cursor.getString(0));
                resourceName = cursor.getString(2);
                //type = cursor.getString(3);

                // Add book to books
                //Log.i("myApp", Integer.toString(id));
                //Log.i("myApp", name + " ( " + type + " ) ");
                output.add(resourceName);
                //Log.i("myApp", cursor.getString(2));
                //Log.i("myapp", output.get(id-1));
            } while (cursor.moveToNext());
        }
        return output;
    }




}
