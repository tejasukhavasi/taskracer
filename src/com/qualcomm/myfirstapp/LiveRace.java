package com.qualcomm.myfirstapp;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LiveRace extends Activity {
	
	private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_race);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Resources res = getResources();
		
		ImageView runner1 = (ImageView) findViewById(R.id.runner1);
		TextView runner1_info = (TextView) findViewById(R.id.runner1_info);
		ProgressBar bar1 = (ProgressBar) findViewById(R.id.sample_user);
		
		setRacerDisplay(runner1, runner1_info, bar1, 0);
		
		mProgress = (ProgressBar) findViewById(R.id.sample_user);
		
		new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doWork(mProgressStatus);

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        }).start();
	}
	
	public int doWork(int current_status){
		try 
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return current_status + 10;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_live_race, menu);
		return true;
	}
	
	public void setRacerDisplay(ImageView img, TextView tview, ProgressBar bar, int gender){
		
		if (gender == 1){
			img.setImageResource(R.drawable.malerunner);
		}
		else
		{
			img.setImageResource(R.drawable.femalerunner);
		}
		tview.setText("Age: 25");
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

}
