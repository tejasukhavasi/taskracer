package com.qualcomm.myfirstapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.qualcommlabs.usercontext.ContentListener;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PermissionChangeListener;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.protocol.ContentDescriptor;
import com.qualcommlabs.usercontext.protocol.ContentEvent;
import com.qualcommlabs.usercontext.protocol.ContextConnectorPermissions;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;

public class CompanyService extends Service implements PlaceEventListener, ContentListener {

    public static final String PLACE_EVENT_DESCRIPTION_KEY = "PLACE_EVENT_DESCRIPTION_KEY";

    static int notificationId = 1;

    private ContextPlaceConnector contextPlaceConnector;
    private ContextCoreConnector contextCoreConnector;
    private PermissionChangeListener subscriptionPermissionChangeListener = new PermissionChangeListener() {

        @Override
        public void permissionChanged(ContextConnectorPermissions contextConnectorPermissions) {
            if (contextConnectorPermissions.isEnabled() == false) {
                ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        contextCoreConnector = ContextCoreConnectorFactory.get(this);
        contextCoreConnector.addContentListener(this);
        contextCoreConnector.addPermissionChangeListener(subscriptionPermissionChangeListener);

        contextPlaceConnector = ContextPlaceConnectorFactory.get(this);
        contextPlaceConnector.addPlaceEventListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        contextCoreConnector.removeContentListener(this);
        contextCoreConnector.removePermissionChangeListener(subscriptionPermissionChangeListener);
        contextPlaceConnector.removePlaceEventListener(this);
    }

    @Override
    public void contentEvent(ContentEvent contentEvent) {
  /*      for (ContentDescriptor contentDescriptor : contentEvent.getContent()) {
            Notification notification = new Notification(R.drawable.icon, contentDescriptor.getTitle(),
                    System.currentTimeMillis());
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notification.ledARGB = 0xff31a2dd;
            notification.ledOnMS = 500;
            notification.ledOffMS = 200;
            notification.flags |= Notification.FLAG_SHOW_LIGHTS;
            notification.defaults |= Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;

            PendingIntent pendingIntent = createPendingIntent(contentDescriptor);

            notification.setLatestEventInfo(this, contentDescriptor.getTitle(),
                    contentDescriptor.getContentDescription(), pendingIntent);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(notificationId, notification);

            notificationId += 1;
        }
   */ 
    }

    @Override
    public void placeEvent(PlaceEvent event) {
        String placeEventDescription = String.format("%s %s", event.getEventType(), event.getPlace().getPlaceName());
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString(PLACE_EVENT_DESCRIPTION_KEY, placeEventDescription);
        editor.commit();
    }

    private PendingIntent createPendingIntent(ContentDescriptor contentDescriptor) {
    /*    Bundle extras = new Bundle();
        extras.putString(ContentActivity.CONTENT_URL_KEY, contentDescriptor.getContentUrl());

        Intent launchIntent = new Intent();
        launchIntent.setClass(this, ContentActivity.class);
        launchIntent.putExtras(extras);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                Integer.parseInt(contentDescriptor.getCampaignId()), launchIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
        */
    	return null;
    }
}
