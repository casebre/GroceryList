package com.example.rafael.grocerylist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

public class NewListActivity extends AppCompatActivity {

    public void confirmNewList(View view) {
        EditText title = (EditText) findViewById(R.id.editListTitle);
        if(title.getText().toString().trim().length() > 3) {

            SQLiteDatabase groceryList = this.openOrCreateDatabase("Grocery", MODE_PRIVATE, null);

            String query = "INSERT INTO GroceryList VALUES (" +
                    "1, '" +
                    title.getText().toString() + "', " +
                    "'2015-12-22', '2015-12-22', 1)";
            //Cursor cursor = groceryList.rawQuery(query, null);
            groceryList.execSQL(query);
            groceryList.close();
            //if(groceryList. != null) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("success", true);
            startActivity(i);

                //Toast.makeText(this, "Title", Toast.LENGTH_LONG);
            /*}
            else
                Toast.makeText(this, "Insert a Title", Toast.LENGTH_LONG);
                */
        }
        else
            Toast.makeText(this, "Insert a Title", Toast.LENGTH_LONG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
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
    }

}
