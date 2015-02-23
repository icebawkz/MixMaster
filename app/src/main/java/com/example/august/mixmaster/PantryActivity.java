package com.example.august.mixmaster;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by August on 2/22/15.
 */
public class PantryActivity extends ActionBarActivity {

    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        //set up shared preferences data
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



    }
}