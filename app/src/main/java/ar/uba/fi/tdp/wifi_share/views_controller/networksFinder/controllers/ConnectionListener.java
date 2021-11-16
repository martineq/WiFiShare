package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.NetworkStatusListener;

public class ConnectionListener extends BroadcastReceiver {

    private NetworkStatusListener networkStatusListener;

    public ConnectionListener(NetworkStatusListener networkStatusListener) {
        this.networkStatusListener = networkStatusListener;
    }

    public void onReceive(Context context, Intent intent) {
        networkStatusListener.statusHasChanged();
    }
}
