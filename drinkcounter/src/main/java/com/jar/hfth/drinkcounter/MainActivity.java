package com.jar.hfth.drinkcounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final SharedPreferences prefs = getSharedPreferences("com.jar.hfth.drinkcounter", Context.MODE_PRIVATE);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);

                Boolean opened = prefs.getBoolean("firstRun", true);
                if (opened){
                    Intent intent = new android.content.Intent(MainActivity.this, RegisterActivity.class);
                    prefs.edit().putBoolean("firstRun", false).commit();
                    prefs.edit().putInt("counter", 0).commit();
                    startActivity(intent);

                }
                if (prefs.getInt("counter", 0) != 0)
                {
                    long time = prefs.getLong("time", System.currentTimeMillis());
                    int counter = prefs.getInt("counter", 0);


                    TextView textView = (TextView) findViewById(R.id.text);
                    int Weight = prefs.getInt("weight", 0);
                    float Gender = prefs.getFloat("gender", 0);

                    float alcoholConsumed;
                    alcoholConsumed = (float)counter*12f*4f*0.06f*1.055f;

                    float constraint;
                    constraint = (float)Weight*Gender;

                    long currentTime = System.currentTimeMillis();
                    currentTime -=time;
                    int hours   = (int) ((currentTime / (1000*60*60)) % 24);
                    float minutes = (float)((currentTime / (1000*60)) % (12*60));
                    minutes = Math.abs(minutes);
                    alcoholConsumed /= constraint;
                    float decay = hours *0.015f;
                    alcoholConsumed -= decay;
                    if (alcoholConsumed < 0)
                        alcoholConsumed = 0;

                    textView.setText("Your BAC is:\n" + Double.valueOf(new DecimalFormat("#.###").format(alcoholConsumed)) +"\n"+counter+" drinks\n"+minutes+" mins\n");
                }

                Button button = (Button) findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long time = prefs.getLong("time", System.currentTimeMillis());
                        int counter = prefs.getInt("counter", 0);
                        counter++;
                        prefs.edit().putInt("counter", counter).commit();
                        TextView textView = (TextView) findViewById(R.id.text);
                        int Weight = prefs.getInt("weight", 0);
                        float Gender = prefs.getFloat("gender", 0);

                        float alcoholConsumed;
                        alcoholConsumed = (float)counter*12f*4f*0.06f*1.055f;

                        float constraint;
                        constraint = (float)Weight*Gender;

                        long currentTime = System.currentTimeMillis();
                        currentTime -=time;
                        int hours   = (int) ((currentTime / (1000*60*60)) % 24);
                        float minutes = (float)((currentTime / (1000*60)) % (12*60));
                        minutes = Math.abs(minutes);
                        alcoholConsumed /= constraint;
                        float decay = hours *0.015f;
                        alcoholConsumed -= decay;
                        if (alcoholConsumed < 0)
                            alcoholConsumed = 0;
                   //     alcoholConsumed /=5f;

                        textView.setText("Your BAC is:\n" + Double.valueOf(new DecimalFormat("#.###").format(alcoholConsumed)) +"\n"+counter+" drinks\n"+minutes+" mins\n");

                    }
                });
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        prefs.edit().putInt("counter", 0).commit();
                        prefs.edit().putLong("time", System.currentTimeMillis()).commit();
                        TextView textView = (TextView) findViewById(R.id.text);
                        textView.setText("0.08 and above are\nconsidered impaired.\nhold '+' to reset");

                        return true;
                    }
                });

            }
        });

    }
}
