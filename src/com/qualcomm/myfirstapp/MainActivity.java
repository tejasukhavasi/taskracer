package com.qualcomm.myfirstapp;

import java.io.IOException;
import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import android.util.Log;

import com.qualcomm.myfirstapp.R;
import com.qualcommlabs.usercontext.Callback;
import com.qualcommlabs.usercontext.ConnectorPermissionChangeListener;
import com.qualcommlabs.usercontext.ContentListener;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextInterestsConnector;
import com.qualcommlabs.usercontext.ContextInterestsConnectorFactory;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PermissionChangeListener;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.StatusCallback;
import com.qualcommlabs.usercontext.protocol.ContentDescriptor;
import com.qualcommlabs.usercontext.protocol.ContentDescriptorHistory;
import com.qualcommlabs.usercontext.protocol.ContentEvent;
import com.qualcommlabs.usercontext.protocol.ContextConnectorError;
import com.qualcommlabs.usercontext.protocol.ContextConnectorPermissions;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;
import com.qualcommlabs.usercontext.protocol.profile.Profile;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;


public class MainActivity extends Activity {

	private static final long DELAY = 3000;
	private boolean scheduled = false;
	private Timer splashTimer;
	
	private static String TAG="rkirti";
	private ContextCoreConnector contextCoreConnector;
	private ContextInterestsConnector contextInterestsConnector;
	private ContextPlaceConnector contextPlaceConnector;

	public ContextCoreConnector getCoreConnector()
	{
		return contextCoreConnector;
	}
	
	public ContextInterestsConnector getInterestsConnector()
	{
		return contextInterestsConnector;
	}
	
	public ContextPlaceConnector getPlaceConnector()
	{
		return contextPlaceConnector;
	}
	
    public static void prettyPrint(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.defaultPrettyPrintingWriter();
        System.out.println(writer.writeValueAsString(obj));
    }

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //setContentView(R.layout.);
        
        setContentView(R.layout.activity_main);
        contextCoreConnector = ContextCoreConnectorFactory.get(this);
        contextInterestsConnector = ContextInterestsConnectorFactory.get(this);
        contextPlaceConnector = ContextPlaceConnectorFactory.get(this);
        Log.e(TAG,"Done getting connectors. Trying to enable the core");
        contextCoreConnector.enable(this, new Callback<Void>() {
        @Override
        	 public void success(Void responseObject) {
        	 // do something when successfully enabled
        	Log.i(TAG,"Core enabled successfully");
        	}
        	
        	 @Override
     	public void failure(int statusCode, String errorMessage) {
         // failed with statusCode
        	Log.i(TAG,"Core enabling failed" + " " + statusCode + " " + errorMessage);	 
        	 }
        	 });
        
        // Requesting the profile here itself for now, should be moved to the right place
        // later
        contextInterestsConnector.requestProfile(new Callback<Profile>() {
        	 @Override
        	 public void success(Profile profile) {
        	 // do something with profile
             Log.i(TAG,"Successfully got the profile");
             try {
				prettyPrint(profile);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        	 }

        	 @Override
        	 public void failure(int statusCode, String errorMessage) {
        	 // failed with errorCode
        	 Log.i(TAG,"Profile fetching failed" + " " + statusCode  + " "  + errorMessage);
        	 }
        	 
        });
        
        	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onPause(){
    	super.onPause();
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
    
    public void openhistory(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
    
}
