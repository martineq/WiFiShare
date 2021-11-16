package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers;

import android.net.wifi.ScanResult;

import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers.AccessPointFinder;

public class AccessPointValidator {

    private AccessPointFinder accessPointFinder;

    public AccessPointValidator(AccessPointFinder accessPointFinder) {
        this.accessPointFinder = accessPointFinder;
    }

    public boolean isNetworkAvailable(Network network) {
        return accessPointFinder.isNetworkAvailable(network);
    }

    public int getSignalStrenght(Network network) {
        ScanResult scanResult = accessPointFinder.searchScanResult(network);
        return ( scanResult == null ) ? 0 : scanResult.level;
    }

    public String getCypherMethod(Network network) {
        ScanResult scanResult = accessPointFinder.searchScanResult(network);
        return ( scanResult == null ) ? "" : scanResult.capabilities;
    }
}
