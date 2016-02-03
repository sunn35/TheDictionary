package com.supermacy.utilities;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;

/**
 * Created by sudhanshu on 2/2/16.
 */
public class CustomConnectivityManager {
    private static Context context;
    private static ConnectivityManager connectivityManager;
    private static NetworkInfo activeNetwork;

    public CustomConnectivityManager(Context context) {
        this.context = context;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = connectivityManager.getActiveNetworkInfo();
    }

    public boolean hasInternetConnectivity() {
        boolean isConnected = (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
        return isConnected;
    }

    public boolean typeOfInternetConnection() {
        boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        return isWifi;
    }
}
