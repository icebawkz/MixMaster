package com.jar.hfth.drinkcounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by August on 3/8/15.
 */
public class RegisterActivity extends Activity {

    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final SharedPreferences prefs = this.getSharedPreferences("com.jar.hfth.drinkcounter", Context.MODE_PRIVATE);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                Button button = (Button) findViewById(R.id.button2);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Spinner weight = (Spinner) findViewById(R.id.weight);
                        Spinner gender = (Spinner) findViewById(R.id.gender);
                        int Weight = Integer.valueOf((String) weight.getSelectedItem());
                        String Gender = (String) gender.getSelectedItem();
                        float factor;
                        Boolean isMale;
                        if(Gender.equals("male"))
                        {
                            factor = 0.73f;
                        }
                        else
                            factor = 0.66f;
                        prefs.edit().putInt("weight", Weight).commit();
                        prefs.edit().putFloat("gender", factor).commit();
                        prefs.edit().putLong("time",System.currentTimeMillis()).commit();
                        finish();
                    }
                });
            }
        });


    }
}
