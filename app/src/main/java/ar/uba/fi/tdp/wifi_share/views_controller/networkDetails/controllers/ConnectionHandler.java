package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.Iterator;

import ar.uba.fi.tdp.wifi_share.model.Network;

public class ConnectionHandler {

    private WifiManager wifiManager;
    private WifiConfiguration wifiConfiguration;

    public ConnectionHandler(Activity activity) {
        wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
    }

    public WifiConfiguration createWiFiConfiguration(Network networkInformation) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "\"" + networkInformation.getName() + "\"";

        if ( networkInformation.getPassword().isEmpty())
            wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        else
            wifiConfiguration.preSharedKey = "\"" + networkInformation.getPassword() + "\""; //WPA

        return wifiConfiguration;
    }

    public void addNetwork(WifiConfiguration wifiConfiguration) {
        wifiConfiguration.networkId = wifiManager.addNetwork(wifiConfiguration);
    }

    public void connect(Network networkInformation) {
        Iterator<WifiConfiguration> networkIterator = wifiManager.getConfiguredNetworks().iterator();

        WifiConfiguration networkConfiguration;
        while( networkIterator.hasNext() && networkIterator != null) {
            networkConfiguration = networkIterator.next();

            if (networkConfiguration.SSID.equals("\"" + networkInformation.getName() + "\"")) {
                networkIterator = null;
                wifiManager.disconnect();
                wifiManager.enableNetwork(networkConfiguration.networkId, true);
                wifiManager.reconnect();
            }
        }
    }

    public void connect(WifiConfiguration wifiConfiguration) {
        wifiManager.disconnect();
        wifiManager.enableNetwork(wifiConfiguration.networkId, true);
        wifiManager.reconnect();
    }

    public WifiConfiguration findConfiguration(Network networkInformation) {
        WifiConfiguration wifiConfiguration = null;
        Iterator<WifiConfiguration> networkIterator = wifiManager.getConfiguredNetworks().iterator();

        while( networkIterator.hasNext() && wifiConfiguration == null) {
            wifiConfiguration = networkIterator.next();

            if ( !wifiConfiguration.SSID.equals("\"" + networkInformation.getName() + "\""))
                wifiConfiguration = null;
        }

        return wifiConfiguration;
    }
}
