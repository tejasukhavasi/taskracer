package com.blundell.tut.ui;


import com.blundell.tut.task.LoadingTask;
import com.blundell.tut.task.LoadingTask.LoadingTaskFinishedListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends Activity implements LoadingTaskFinishedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the splash screen
        setContentView(com.qualcomm.myfirstapp.R.layout.activity_splash);
        // Find the progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(com.qualcomm.myfirstapp.R.id.activity_splash_progress_bar);
        // Start your loading
        new LoadingTask(progressBar, this).execute("www.google.co.uk"); // Pass in whatever you need a url is just an example we don't use it in this tutorial
    }

    // This is the callback for when your async task has finished
    @Override
	public void onTaskFinished() {
		completeSplash();
	}

    private void completeSplash(){
		startApp();
		finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }

    private void startApp() {
		Intent intent = new Intent(SplashActivity.this, com.qualcomm.myfirstapp.MainActivity.class);
		startActivity(intent);
	}
}