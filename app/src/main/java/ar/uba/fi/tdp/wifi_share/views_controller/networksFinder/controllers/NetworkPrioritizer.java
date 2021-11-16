package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers;

import android.app.Activity;

import ar.uba.fi.tdp.wifi_share.model.ConnectionQualitySolver;
import ar.uba.fi.tdp.wifi_share.model.NetworkSolver;
import ar.uba.fi.tdp.wifi_share.model.NetworksDatabase;
import ar.uba.fi.tdp.wifi_share.model.ProximitySolver;

public class NetworkPrioritizer {

    public enum NetworkPrioritzationType { PRIORITIZE_PROXIMITY, PRIORITIZE_CALIFICATION };

    private NetworkPrioritzationType currentState;
    private Activity activity;

    public NetworkPrioritizer(Activity activity) {
        this.currentState = NetworkPrioritzationType.PRIORITIZE_PROXIMITY;
        this.activity = activity;
    }

    public void changeCurrentPriorityType(NetworkPrioritzationType type) {
        this.currentState = type;
    }

    public AccessPoints getAccessPointList(NetworksDatabase networksDatabase) {

        NetworkSolver networkSorter = null;

        if ( this.currentState == NetworkPrioritzationType.PRIORITIZE_PROXIMITY )
            networkSorter = new ProximitySolver(NetworksDatabase.accessDatabase(), activity);

        if ( this.currentState == NetworkPrioritzationType.PRIORITIZE_CALIFICATION )
            networkSorter = new ConnectionQualitySolver(NetworksDatabase.accessDatabase());

        networkSorter.loadNetworks();
        return networkSorter.solve();
    }

}
