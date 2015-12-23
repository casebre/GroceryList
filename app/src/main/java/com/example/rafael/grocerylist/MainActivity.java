package com.example.rafael.grocerylist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public void addNewList(View view) {
        Intent i = new Intent(getApplicationContext(), NewListActivity.class);
        startActivity(i);
    }

    public void showDetails(View view) {
        Intent i = new Intent(getApplicationContext(), DetailActivity.class);
        i.putExtra("List", 1);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView list = (ListView)findViewById(R.id.listViewGroceryList);

        try {

            SQLiteDatabase groceryList = this.openOrCreateDatabase("Grocery", MODE_PRIVATE, null);

            String query = "CREATE TABLE IF NOT EXISTS GroceryList (" +
                    "IdList INT," +
                    "Name VARCHAR," +
                    "CreateDate DATETIME, " +
                    "UpdateDate DATETIME," +
                    "Active INT) ";

            groceryList.execSQL(query);

            query = "SELECT rowid _id   , " +
                    "Name " +
                    "FROM GroceryList ";

            Cursor cursor = groceryList.rawQuery(query, null);

            String[] columns = new String[] {"Name"};
            int[] to = new int[] {R.id.textView5};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_main, cursor, columns, to,0);


            /*if (cursor != null && cursor.moveToFirst()) {
                do {
                    listMap.put(cursor.getInt(0),cursor.getString(1));
                } while (cursor.moveToNext());
            }
            */
            list.setAdapter(adapter);
            groceryList.close();
        }
        catch (Exception e) {

            e.printStackTrace();

        }
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,createdList);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("ListId", position);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
