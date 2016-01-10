package com.example.rafael.grocerylist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<GroceryList> listGrocery = null;
    int start = 0, end = 5, position = 0;
    public final int BLOCK = 5;

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
        //fab.setImageResource(R.drawable.ic_menu_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                Intent i = new Intent(getApplicationContext(), NewListActivity.class);
                startActivity(i);
            }
        });



        final ListView list = (ListView)findViewById(R.id.listViewGroceryList);
        listGrocery = GroceryList.List(this);


        final CustomAdapterGrocery[] adapterGrocery = {new CustomAdapterGrocery(this, listGrocery.subList(start, end))};
        list.setAdapter(adapterGrocery[0]);

            if (listGrocery.size() > end) {
                // Creating new button that allow users to show more items
                Button loadMore = new Button(this);
                loadMore.setText("Load More");
                list.addFooterView(loadMore);

                loadMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listGrocery.size() < (end+BLOCK))
                            end = listGrocery.size();
                        else
                            end += BLOCK;

                        position = list.getFirstVisiblePosition();

                        adapterGrocery[0] = new CustomAdapterGrocery(MainActivity.this, listGrocery.subList(start, end));
                        list.setAdapter(adapterGrocery[0]);
                        list.setSelectionFromTop(position + 1, 0);

                    }
                });
            }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("ListId", position);
                startActivity(i);
            }
        });
    }

    public void onTaskCompleted() {
        Toast.makeText(this, "Task has finished", Toast.LENGTH_SHORT).show();
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

    public class PerformTask extends AsyncTask {

        private OnTaskCompleted listener;

        public void PerformTask(OnTaskCompleted listener) {
            this.listener = listener;
        }
        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        protected void onPostExecute(String s) {
            listener.onTaskCompleted();
        }
    }
}
