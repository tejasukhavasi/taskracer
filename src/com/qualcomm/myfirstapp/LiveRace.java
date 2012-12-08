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

    private ProgressBar mProgress_1, mProgress_2, mProgress_you;
    private int mProgress_1_Status = 0;
    private int mProgress_2_Status = 0;
    private int mProgress_you_Status = 0;

    private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_race);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Resources res = getResources();
		
		ImageView runner1 = (ImageView) findViewById(R.id.runner1);
		TextView runner1_info = (TextView) findViewById(R.id.runner1_info);
		ProgressBar bar1 = (ProgressBar) findViewById(R.id.sample_user_1);
		
		ImageView runner2 = (ImageView) findViewById(R.id.runner2);
		TextView runner2_info = (TextView) findViewById(R.id.runner2_info);
		ProgressBar bar2 = (ProgressBar) findViewById(R.id.sample_user_2);
		
		ImageView runner_you = (ImageView) findViewById(R.id.you);
		TextView you_info = (TextView) findViewById(R.id.you_info);
		ProgressBar you_bar = (ProgressBar) findViewById(R.id.sample_user_you);
		
		setRacerDisplay(runner1, runner1_info, bar1, 0, 27,0);
		
		setRacerDisplay(runner2, runner2_info, bar2, 0, 25,1);
		
		setRacerDisplay(runner_you, you_info, you_bar, 0, 28,2);
		
		mProgress_1 = (ProgressBar) findViewById(R.id.sample_user_1);
		
		mProgress_2 = (ProgressBar) findViewById(R.id.sample_user_2);
		
		mProgress_you = (ProgressBar) findViewById(R.id.sample_user_you);
		
		new Thread(new Runnable() {
            public void run() {
                while (mProgress_1_Status < 100) {
                	mProgress_1_Status = doWork(0, mProgress_1_Status);

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress_1.setProgress(mProgress_1_Status);
                        }
                    });
                }
            }
        }).start();
		
		new Thread(new Runnable() {
            public void run() {
                while (mProgress_2_Status < 100) {
                	mProgress_2_Status = doWork(1, mProgress_2_Status);

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress_2.setProgress(mProgress_2_Status);
                        }
                    });
                }
            }
        }).start();
		
		new Thread(new Runnable() {
            public void run() {
                while (mProgress_you_Status < 100) {
                	mProgress_you_Status = doWork(2, mProgress_you_Status);

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress_you.setProgress(mProgress_you_Status);
                        }
                    });
                }
            }
        }).start();
	}
	
	/*public int doWork(int current_status){
		try 
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return current_status + 10;
	}*/
    public int doWork(int id, int current_status){
        try 
        {
               Thread.sleep(1000);
        }
        catch (InterruptedException e) {
               e.printStackTrace();
        }
        switch (id)
        {
        case 0: 
               return current_status + 5;
        case 1:
               if (current_status < 28)
               {
                     
                     return current_status + 3;
               }
               else
               {
                     if (current_status < 100)
                            return current_status + 8;
                     else 
                            return 100;
               }
               //return current_status + 12;      
        case 2:
               return current_status + 3; 
        }
        return current_status;
 }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_live_race, menu);
		return true;
	}
	
	public void setRacerDisplay(ImageView img, TextView tview, ProgressBar bar, int gender, int age, int uid){
	
		if (gender == 1){
			img.setImageResource(R.drawable.malerunner);
		}
		else
		{
			img.setImageResource(R.drawable.femalerunner);
		}
		if (uid == 2)
		{
			img.setImageResource(R.drawable.ic_launcher);
		}
		
		switch (uid)
		{
		case 0:
			tview.setText(age+"F, SF, CA");
			break;
		case 1:
			tview.setText(age+"F, BO, MA");
			break;
		case 2:
			tview.setText("me, SD, CA");
			break;
		}
		
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
