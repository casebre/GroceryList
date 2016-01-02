package com.example.rafael.grocerylist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by rafael on 01/01/16.
 */
public class GroceryList {

    private Integer id;
    private String name;
    private Context context;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setId(String name) {
        this.name = name;
    }

    public GroceryList() {

    }

    public GroceryList(Context context, Integer id) {
        this.id = id;
        this.context = context;

        try {
            SQLiteDatabase groceryList = this.context.openOrCreateDatabase("Grocery", Context.MODE_PRIVATE, null); //("Grocery", Context.MODE_PRIVATE, null, null);

            /* String query = "CREATE TABLE IF NOT EXISTS GroceryList (" +
                    "IdList INT," +
                    "Name VARCHAR," +
                    "CreateDate DATETIME, " +
                    "UpdateDate DATETIME," +
                    "Active INT) ";
                    */

            String query =
                    "SELECT rowid _id   , " +
                            "Name " +
                            "FROM GroceryList " +
                            "WHERE _id = " + id.toString();

            Cursor cursor = groceryList.rawQuery(query, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    this.name = cursor.getString(1);
                } while (cursor.moveToNext());
            }
            cursor.close();
            groceryList.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean Save() {
        //SQLiteDatabase groceryList = openDatabase("Grocery", this.context.MODE_PRIVATE, null); //context.openOrCreateDatabase("Grocery", Context.MODE_PRIVATE, null);
        return false;
    }

    public static List<GroceryList> List(Context context){

        List<GroceryList> list = new ArrayList<GroceryList>();


        try {
            SQLiteDatabase groceryList = context.openOrCreateDatabase("Grocery", Context.MODE_PRIVATE, null); //("Grocery", Context.MODE_PRIVATE, null, null);

            /* String query = "CREATE TABLE IF NOT EXISTS GroceryList (" +
                    "IdList INT," +
                    "Name VARCHAR," +
                    "CreateDate DATETIME, " +
                    "UpdateDate DATETIME," +
                    "Active INT) ";
                    */

            String query =
                    "SELECT rowid _id   , " +
                            "Name " +
                            "FROM GroceryList ";

            Cursor cursor = groceryList.rawQuery(query, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    GroceryList grocery = new GroceryList();
                    grocery.id = cursor.getInt(0);
                    grocery.name = cursor.getString(1);
                    list.add(grocery);

                } while (cursor.moveToNext());
            }
            cursor.close();
            groceryList.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
