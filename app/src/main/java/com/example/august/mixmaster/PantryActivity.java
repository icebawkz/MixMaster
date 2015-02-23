package com.example.august.mixmaster;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2/22/15.
 */
public class PantryActivity extends ActionBarActivity {
    private Spinner spinner1, spinner2;
    final List<Bottles> drinks = new ArrayList<>();
    private Button btnSubmit;
    final Context context = this;

    //set up shared preferences data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        addListenerOnButton();
        update();
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final boolean firstpantry = preferences.getBoolean("firstpantry", true);
        //dialog box to welcome users to the pantry
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Welcome to the Pantry");
        builder.setMessage("This is where we can keep track of what ingredients you have on hand, that way, we can tell you what drinks you can mix!")
                .setPositiveButton("got it", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        preferences.edit().putBoolean("firstpantry", false).commit();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("I'm not interested", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Intent intent = new Intent(PantryActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        // Create the AlertDialog object and return it
        if (firstpantry) {
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }



        //populate list view
        final ListView listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(new BottleAdapter(this, R.layout.bottle_list, drinks));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Bottles drink = drinks.get(position);
                Toast.makeText(PantryActivity.this,
                        "Removed " + drink.getName() + " from your pantry",
                        Toast.LENGTH_SHORT).show();

                preferences.edit().putBoolean(String.valueOf(drink.getName()), false).commit();
                drinks.remove(drink);
                update();
            }



        });

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.button);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(PantryActivity.this,
                        "Added " +
                                 String.valueOf(spinner1.getSelectedItem()) +
                                " to your pantry",
                        Toast.LENGTH_SHORT).show();
                preferences.edit().putBoolean(String.valueOf(spinner1.getSelectedItem()), true).commit();
                update();


            }

        });
    }

    public void update(){

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        BottleAdapter adapt = new BottleAdapter(this, R.layout.bottle_list, drinks);
        final boolean gin = preferences.getBoolean("Gin", false);
        final boolean vodka = preferences.getBoolean("Vodka", false);
        final boolean rum = preferences.getBoolean("Rum", false);
        final boolean whiskey = preferences.getBoolean("Whiskey", false);
        final boolean tequila = preferences.getBoolean("Tequila", false);
        final boolean jagger = preferences.getBoolean("Jager", false);
        ListView listView = (ListView) findViewById(R.id.listView2);
        adapt.clear();
        adapt.notifyDataSetChanged();
        listView.setAdapter(null);
        if (gin) {
            drinks.add(new Bottles("Gin"));
        }
        if (vodka) {
            drinks.add(new Bottles("Vodka"));
        }
        if (rum) {
            drinks.add(new Bottles("Rum"));
        }
        if (whiskey) {
            drinks.add(new Bottles("Whiskey"));
        }
        if (tequila) {
            drinks.add(new Bottles("Tequila"));
        }
        if (jagger) {
            drinks.add(new Bottles("Jager"));
        }
        /*
        preferences.edit().putBoolean("Gin", false).commit();
        preferences.edit().putBoolean("Vodka", false).commit();
        preferences.edit().putBoolean("Rum", false).commit();
        preferences.edit().putBoolean("Whiskey", false).commit();
        preferences.edit().putBoolean("Tequila", false).commit();
        preferences.edit().putBoolean("Jager", false).commit();
*/

        listView.setAdapter(new BottleAdapter(PantryActivity.this, R.layout.bottle_list, drinks));

    }
}