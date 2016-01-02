package com.example.rafael.grocerylist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rafael on 01/01/16.
 */
public class CustomAdapterGrocery extends BaseAdapter {

    private Activity activity;
    private Context context;
    private List<GroceryList> list = null; //new HashMap<Integer, String>();
    private LayoutInflater inflater = null;


    public CustomAdapterGrocery(Context context, List<GroceryList> groceryList) {
        this.context = context;
        this.list = groceryList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            //inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grocerylist_list, parent, false);
        }

        GroceryList grocery = new GroceryList(context,1);


        TextView textView;
        textView = (TextView) convertView.findViewById(R.id.textViewTitle);
        textView.setText(list.get(position).getName());

        ImageView imageView;
        imageView = (ImageView) convertView.findViewById(R.id.imageGroceyLogo);
        imageView.setImageResource(R.drawable.freshco);
        // TODO: Create a class to add new grocery stores
        // TODO: link the Grocery Store to the List


        return convertView;
    }
}
