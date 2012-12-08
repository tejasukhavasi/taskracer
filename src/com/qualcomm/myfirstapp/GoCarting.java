package com.qualcomm.myfirstapp;

import com.qualcomm.myfirstapp.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GoCarting extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_go_carting);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Resources res = getResources();
		TextView tview1 = (TextView) findViewById(R.id.gocarting_input_1);
		TextView tview2 = (TextView) findViewById(R.id.gocarting_input_2);
		fillTextView(tview1,res.getString(R.string.gocarting_input_1_defaultval));
		fillTextView(tview2,res.getString(R.string.gocarting_input_2_defaultval));
		
		tview1.setOnKeyListener(onEnterFocusDown);
		tview2.setOnKeyListener(onEnterStartRace);
	}
	
	public void fillTextView(TextView tview, String str) {
		tview.setText(str);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_go_carting, menu);
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
	/** Called when the user clicks the 'Race now!' button */
    public void startrace(View view) {
    	//Process the user input
    	EditText numItems_userin = (EditText) findViewById(R.id.gocarting_input_1);
    	EditText estTime_userin = (EditText) findViewById(R.id.gocarting_input_2);
    	String numItems_text = numItems_userin.getText().toString();
    	int numItems_val = Integer.parseInt(numItems_text);
    	String estTime_text = estTime_userin.getText().toString();
    	int estTime_val = Integer.parseInt(estTime_text);
    	
        // Do something in response to button
    	Intent intent = new Intent(this, LiveRace.class);
        startActivity(intent);
    }
    
    /* OnKeyListener that puts the focus down when the ENTER key is pressed
    */
   protected View.OnKeyListener onEnterFocusDown = new View.OnKeyListener() {

                   @Override
                   public boolean onKey(View v, int keyCode, KeyEvent event) {
                   if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                               (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                   v.requestFocus(View.FOCUS_DOWN);
                           return true;
                   }
                           return false;
                   }
           };

   /**
    * OnKeyListener that submits the page when the ENTER key is pressed
    */
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
