package com.qualcomm.myfirstapp;


import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewRace extends ListActivity{
	
	String[] RACE_TYPES;//new String[] {"Go carting", "Bed to Boots"};//
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Resources res = getResources();
		RACE_TYPES =  res.getStringArray(R.array.race_types);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, RACE_TYPES);
		setListAdapter(adapter);
	}
	
	public void onPause(){
		super.onPause();
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
	    String pen = adapter.getItem(position);


	    if (RACE_TYPES[0].equals(pen))
	    { 
	        Intent intent = new Intent(v.getContext(), GoCarting.class);
	        startActivity(intent);
	    }
	    if (RACE_TYPES[1].equals(pen))
	    {
	    	Intent intent = new Intent(v.getContext(), FireInTheHome.class);
	        startActivity(intent);
	    }
	    if (RACE_TYPES[2].equals(pen))
	    {
	    	Intent intent = new Intent(v.getContext(), RiseAndShine.class);
	        startActivity(intent);
	    }
	}
}


/*public class NewRace extends Activity{
	private static String TAG="TSUKHAVA";
	//private static String[] RACE_TYPES = new String[] {"Go carting", "Fire in the home"};
	
	String[] RACE_TYPES = new String[] {"Go carting", "Fire in the home"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_race);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ListView listview = (ListView) findViewById(R.id.tasklist);

		// This is the Adapter being used to display the list's data
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, RACE_TYPES);
		
		listview.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_race, menu);
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
}*/
