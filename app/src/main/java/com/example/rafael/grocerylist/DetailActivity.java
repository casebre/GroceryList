package com.example.rafael.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public void showLists(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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

        Intent i = getIntent();
        int listId = i.getIntExtra("ListId",-1);

        if (listId != -1) {
            TextView textView;
            switch (listId) {
                case 0:
                    textView = (TextView) findViewById(R.id.textView3);
                    textView.setText("Freshco!!");
                    break;
                default:
                    textView = (TextView) findViewById(R.id.textView3);
                    textView.setText("No data found");
                    break;
            }
        }
        else
            Log.i("", "List found!");


    }

}
