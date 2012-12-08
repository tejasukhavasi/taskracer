package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class FireInTheHome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fire_in_the_home);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Resources res = getResources();
		TextView tview1 = (TextView) findViewById(R.id.riseandshine_input_1);
		
		fillTextView(tview1, res.getString(R.string.riseandshine_input_1_defaultval));
		
		tview1.setOnKeyListener(onEnterStartRace);
	}
	
	public void fillTextView(TextView tview, String str) {
		tview.setText(str);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fire_in_the_home, menu);
		return true;
	}
	
	public void onPause(){
		super.onPause();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void startrace(View view) {
		Resources res = getResources();
		//Process the user input
    	EditText estTime_userin = (EditText) findViewById(R.id.riseandshine_input_1);
    	String estTime_text = estTime_userin.getText().toString();
    	int estTime_val  = 0;
    	try{
    		estTime_val = Integer.parseInt(estTime_text);
    	}
    	catch(NumberFormatException error){
    		//use default value in this case
    		String temp = res.getString(R.string.gocarting_input_1_defaultval);
    		estTime_val = Integer.parseInt(temp);
    	}
        // Do something in response to button
    	Intent intent = new Intent(this, LiveRace.class);
        startActivity(intent);
    }
	
	protected View.OnKeyListener onEnterStartRace = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        startrace(v);
                return true;
        }
                return false;
        }
};

}
