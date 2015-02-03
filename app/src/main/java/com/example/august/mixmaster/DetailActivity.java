package com.example.august.mixmaster;

/**
 * Created by August on 1/31/15.
 */

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name1 = intent.getStringExtra("name");
            int fileName1 = intent.getIntExtra("fileName", 0);
            String about1 = intent.getStringExtra("about");
            String how = intent.getStringExtra("how");

            // TODO(ago): Set name and image into the activity_detail layout file.

            ImageView imageView = (ImageView) findViewById(R.id.photo);
            TextView drinkName = (TextView) findViewById(R.id.namez);
            TextView About = (TextView) findViewById(R.id.about);
            TextView How = (TextView) findViewById(R.id.textView);

            drinkName.setText(name1);
            About.setText(about1);
            imageView.setImageResource(fileName1);
            How.setText(how);

        }

    }
}


