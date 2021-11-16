package ar.uba.fi.tdp.wifi_share.model;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers.AccessPoints;

public class ProximitySolver implements NetworkSolver {

    private NetworksDatabase networksDatabase;
    private ArrayList<NetworkMeasurement> networksProximities;
    private LocationManager locationManager;

    public ProximitySolver(NetworksDatabase database, Activity activity) {

        this.networksDatabase = database;
        this.networksProximities = new ArrayList<>();

        this.locationManager = (LocationManager) activity.getSystemService(activity.LOCATION_SERVICE);
    }

    public void loadNetworks() {

        try {
            Location location = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Site myPosition = new Site(location.getLatitude(), location.getLongitude(), "", 0);
            Iterator<Map.Entry<String, Network>> networkIterator = networksDatabase.getNetworkIterator();

            while (networkIterator.hasNext()) {
                Network network = networkIterator.next().getValue();
                double distance = myPosition.calculateDistance(network.getSite());
                this.networksProximities.add(new NetworkMeasurement(network, distance));
            }
        }

        catch (SecurityException se) {

        }
    }

    public AccessPoints solve() {

        Collections.sort(this.networksProximities);

        AccessPoints accessPoints = new AccessPoints();

        for ( NetworkMeasurement networkProximity : this.networksProximities ) {
                accessPoints.add(networkProximity.getNetwork());
        }

        return accessPoints;
    }
}
