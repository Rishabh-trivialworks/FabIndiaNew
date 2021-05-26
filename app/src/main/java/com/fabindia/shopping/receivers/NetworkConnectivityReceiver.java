package com.fabindia.shopping.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.fabindia.shopping.listeners.NetworChangeListener;


public class NetworkConnectivityReceiver extends BroadcastReceiver {

    NetworChangeListener mlistener;
    public NetworkConnectivityReceiver(NetworChangeListener listner){
        this.mlistener = listner;
    }
    public NetworkConnectivityReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try
        {
            if(mlistener!=null){
                mlistener.onNetworkChange(isOnline(context));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
