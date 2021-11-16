package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.Iterator;
import java.util.List;

import ar.uba.fi.tdp.wifi_share.model.Network;

public class AccessPointFinder {

    private WifiManager wifiManager;

    public AccessPointFinder(Activity activity) {
        wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
    }

    public void scanAccessPoints(AccessPoints accessPoints) {

        List<ScanResult> scanResults = wifiManager.getScanResults();

        for( ScanResult result: scanResults ) {
            Network network = new Network( result.SSID, result.BSSID );
            network.setCapabilities(result.capabilities);
            network.setSignalStrengh(result.level);
            accessPoints.add(network);
        }
    }

    public ScanResult searchScanResult(Network network) {
        ScanResult scanResult = null;

        List<ScanResult> scanResults = wifiManager.getScanResults();
        Iterator<ScanResult> iterator = scanResults.iterator();

        boolean networkAvailability = false;
        while ( networkAvailability == false && iterator.hasNext() ) {
            scanResult = iterator.next();
            networkAvailability = ( network.getName().equals(scanResult.SSID) );
        }

        if ( networkAvailability == false )
            scanResult = null;

        return scanResult;
    }

    public boolean isNetworkAvailable(Network network) {
        return this.searchScanResult(network) == null ? false : true;
    }
}
