package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers;

import android.app.Activity;
import android.net.wifi.WifiConfiguration;

import ar.uba.fi.tdp.wifi_share.model.Network;

public class ConnectionFacilitator {

    private ConnectionHandler connectionHandler;

    public ConnectionFacilitator(Activity activity) {
        connectionHandler = new ConnectionHandler(activity);
    }

    public void connect(Network networkInformation){
        WifiConfiguration wifiConfiguration = connectionHandler.findConfiguration(networkInformation);

        if ( wifiConfiguration == null ) {
            wifiConfiguration = connectionHandler.createWiFiConfiguration(networkInformation);
            connectionHandler.addNetwork(wifiConfiguration);
        }

        connectionHandler.connect(wifiConfiguration);
    }
}
