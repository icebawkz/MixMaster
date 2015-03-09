package com.example.august.mixmaster;

/**
 * Created by August on 1/31/15.
 */

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by August on 1/23/15.
 */
public class DetailActivity extends ActionBarActivity {

    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name1 = intent.getStringExtra("name");
            int fileName1 = intent.getIntExtra("fileName", 0);
            String about1 = intent.getStringExtra("about");
            String how = intent.getStringExtra("how");
            String ingredients[] = intent.getStringArrayExtra("ingredients");

            // TODO(ago): Set name and image into the activity_detail layout file.

            ImageView imageView = (ImageView) findViewById(R.id.photo);
            TextView drinkName = (TextView) findViewById(R.id.namez);
            TextView About = (TextView) findViewById(R.id.about);
            TextView How = (TextView) findViewById(R.id.textView);
            TextView items = (TextView) findViewById(R.id.items);

            // Find out if they own the ingredients
            final boolean have = preferences.getBoolean(ingredients[0], false);
            if(ingredients.length > 1) {
                final boolean have2 = preferences.getBoolean(ingredients[1], false);
                if (have && have2) {
                    items.setText("You have everything you need to make this.");
                } else if (have) {
                    items.setText("You need to buy " + ingredients[1] + " for this drink.");
                } else if (have2) {
                    items.setText("You need to buy " + ingredients[0] + " for this drink.");
                } else {
                    items.setText("You need to buy " + ingredients[0] + " and " + ingredients[1] + " for this drink.");
                }
            }
            else
            {
                if(have)
                    items.setText("You have everything you need to make this.");
                else
                    items.setText("You need to buy " + ingredients[0] + " for this drink.");
            }

            drinkName.setText(name1);
            About.setText(about1);
            imageView.setImageResource(fileName1);
            How.setText(how);

        }

    }
}


