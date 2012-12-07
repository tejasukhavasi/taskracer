package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /** Called when the user clicks the 'Race me up!' button */
    public void race_me_up(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, NewRace.class);
        startActivity(intent);
    }
    
    /** Called when the user clicks the 'Statistics' button */
    public void statistics(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }
    
}
