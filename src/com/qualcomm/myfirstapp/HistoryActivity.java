package com.qualcomm.myfirstapp;

import com.qualcomm.myfirstapp.History;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HistoryActivity extends Activity {

	TableLayout history_table;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		history_table=(TableLayout)findViewById(R.id.history_table);
        fillHistoryTable();
 }
 void fillHistoryTable() {

        TableRow row;
        TextView t1, t2, t3;
        //Converting to dip unit
        int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                     (float) 1, getResources().getDisplayMetrics());

        for (int current = 0; current < History.Date.length; current++) {
               row = new TableRow(this);

               t1 = new TextView(this);
               // t1.setTextColor(getResources().getColor(R.color.black));
               t2 = new TextView(this);
               // t2.setTextColor(getResources().getColor(R.color.black));
               t3 = new TextView(this);

               t1.setText(History.Date[current]);
               t2.setText(History.Duration[current]);
               t3.setText(History.Outcome[current]);

               t1.setTypeface(null, 1);
               t2.setTypeface(null, 1);
               t3.setTypeface(null, 1);

               t1.setTextSize(15);
               t2.setTextSize(15);
               t3.setTextSize(15);

               t1.setWidth(50 * dip);
               t2.setWidth(30 * dip);
               t3.setWidth(70 * dip);
               //t1.setPadding(20*dip, 0, 0, 0);
               row.addView(t1);
               row.addView(t2);
               row.addView(t3);

               history_table.addView(row, new TableLayout.LayoutParams(
                            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_history, menu);
		return true;
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
