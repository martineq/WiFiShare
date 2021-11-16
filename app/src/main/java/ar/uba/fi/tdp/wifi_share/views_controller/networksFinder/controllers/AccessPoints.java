package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Iterator;
import java.util.Vector;

import ar.uba.fi.tdp.wifi_share.model.Network;

public class AccessPoints {

    private int sponsoredNetworksQuantity;
    private Vector<Network> networks;

    public AccessPoints(){
        networks = new Vector<Network>();
        sponsoredNetworksQuantity = 0;
    }

    public int getSponsoredNetworksQuantity() {
        return sponsoredNetworksQuantity;
    }

    public void setSponsoredNetworksQuantity(int sponsoredNetworksQuantity) {
        this.sponsoredNetworksQuantity = sponsoredNetworksQuantity;
    }

    public void add(Network network){
        networks.add(network);
    }

    public void remove(Network network) {
        networks.remove(network);
    }

    public ArrayAdapter<Network> createDistanceAdapter(Context context) {
        return new NetworkProximityAdapter(context, this.createNetworkArray(), sponsoredNetworksQuantity);
    }

    public ArrayAdapter<Network> createRatingAdapter(Context context){
        return new NetworkRankingAdapter(context, this.createNetworkArray(), sponsoredNetworksQuantity);
    }

    private Network [] createNetworkArray() {
        int i = 0;
        Network []arrayNetworks = new Network[networks.size()];
        for ( Network network : networks )
            arrayNetworks[i++] = network;

        return arrayNetworks;
    }

    public void rearrangeSponsoredNetworks() {
        int i = 0;
        int j = 0;
        Iterator<Network> iterator = this.networks.iterator();

        while( i < sponsoredNetworksQuantity && iterator.hasNext() ) {
            Network network = iterator.next();

            if (network.getSponsorshipStatus() == Network.Sponsorship.SPONSORED) {
                if ( i != j ) {
                    networks.removeElement(network);
                    networks.insertElementAt(network, i);
                    iterator = networks.listIterator(j);
                }
                i++;
            }
            j++;
        }
    }
}
