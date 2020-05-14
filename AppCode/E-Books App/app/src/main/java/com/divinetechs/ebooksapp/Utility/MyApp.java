package com.divinetechs.ebooksapp.Utility;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.divinetechs.ebooksapp.R;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.onesignal.OneSignal;

public class MyApp extends Application {

    private static MyApp singleton = null;
    private static final String TAG = MyApp.class.getSimpleName();
    PrefManager prefManager;

    @Override
    public void onCreate() {
        super.onCreate();

        singleton = this;
        prefManager=new PrefManager(this);

        Log.e("ID==>",""+prefManager.getValue("publisher_id"));
        MobileAds.initialize(this, prefManager.getValue("publisher_id"));
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        FirebaseApp.initializeApp(this);

    }

    public void initAppLanguage(Context context) {

        LocaleUtils.initialize(context, LocaleUtils.getSelectedLanguageId());

    }


    public static MyApp getPhotoApp() {
        return singleton;
    }

    public Context getContext() {
        return singleton.getContext();
    }

    public static MyApp getInstance() {
        if (singleton == null) {
            singleton = new MyApp();
        }
        return singleton;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}