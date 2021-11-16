package ar.uba.fi.tdp.wifi_share.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers.AccessPoints;

public class ConnectionQualitySolver implements NetworkSolver {

    private NetworksDatabase networksDatabase;
    private ArrayList<NetworkMeasurement> networksConnectionQuality;

    public ConnectionQualitySolver(NetworksDatabase database) {

        this.networksDatabase = database;
        this.networksConnectionQuality = new ArrayList<>();
    }

    public void loadNetworks() {

        try {
            Iterator<Map.Entry<String, Network>> networkIterator = networksDatabase.getNetworkIterator();

            while (networkIterator.hasNext()) {
                Network network = networkIterator.next().getValue();
                this.networksConnectionQuality.add(new NetworkMeasurement(network, network.getRanking()));
            }
        }

        catch (SecurityException se) {

        }
    }

    public AccessPoints solve() {

        Collections.sort(this.networksConnectionQuality);
        Collections.reverse(this.networksConnectionQuality);

        AccessPoints accessPoints = new AccessPoints();

        for ( NetworkMeasurement networkProximity : this.networksConnectionQuality ) {
                accessPoints.add(networkProximity.getNetwork());
        }

        return accessPoints;
    }
}
