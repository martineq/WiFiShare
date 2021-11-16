package ar.uba.fi.tdp.wifi_share.controllers;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WiFiStatusController {

    private WifiManager wifiManager;

    public WiFiStatusController(Activity activity) {
        wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
    }

    public void activateWifi() {
        if ( wifiManager.isWifiEnabled() == false ) {
            wifiManager.setWifiEnabled(true);
        }
    }

    public WifiInfo getCurrentConnectedWiFiInformation() {
        return wifiManager.getConnectionInfo();
    }
}
