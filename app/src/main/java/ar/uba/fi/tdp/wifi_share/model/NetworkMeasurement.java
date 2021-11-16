package ar.uba.fi.tdp.wifi_share.model;

public class NetworkMeasurement implements Comparable<NetworkMeasurement> {

    private Network network;
    private double distance;

    public NetworkMeasurement(Network network, double distance) {
        this.network = network;
        this.distance = distance;
    }

    public Network getNetwork() {
        return this.network;
    }

    public double getNetworkMeasurement() {
        return this.distance;
    }

    public int compareTo(NetworkMeasurement another) {

        return new Double(this.distance - another.distance).intValue();
    }
}
