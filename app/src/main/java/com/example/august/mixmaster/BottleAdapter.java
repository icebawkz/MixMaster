package com.example.august.mixmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by August on 2/22/15.
 */
public class BottleAdapter extends ArrayAdapter<Bottles> {

    private final List<Bottles> drinks;

    public BottleAdapter(Context context, int resource, List<Bottles> drinks){

        super(context, resource, drinks);
        this.drinks = drinks;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return getDrinkView(position, convertView, parent);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getDrinkView(position, convertView, parent);
    }


    public View getDrinkView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.bottle_list, null);
        TextView textView = (TextView)row.findViewById(R.id.textView5);
        textView.setText(drinks.get(position).getName());


        return row; }
}

