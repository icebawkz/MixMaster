package com.example.august.mixmaster;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.august.mixmaster.Recipes;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by August on 1/23/15.
 */
public class BoozeAdapter extends ArrayAdapter<Recipes> {

    private final List<Recipes> drinks;

    public BoozeAdapter(Context context, int resource, List<Recipes> drinks){

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

        View row = inflater.inflate(R.layout.list_single_entry, null);
        TextView textView = (TextView)row.findViewById(R.id.name);
        textView.setText(drinks.get(position).getName());



        ImageView imageView = (ImageView) row.findViewById(R.id.photo);
        imageView.setImageResource(drinks.get(position).getSmall());


        return row; }
}
