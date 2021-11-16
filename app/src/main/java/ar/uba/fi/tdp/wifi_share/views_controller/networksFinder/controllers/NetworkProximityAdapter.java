package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.model.Site;

public class NetworkProximityAdapter extends ArrayAdapter<Network> {

    private Context context;
    private Network[] networks;
    private Site myPosition;
    private int sponsoredNetworksQuantity;

    public NetworkProximityAdapter(Context context, Network[] networks, int sponsoredNetworksQuantity) {
        super(context, -1, networks);
        this.context = context;
        this.networks = networks;
        this.sponsoredNetworksQuantity = sponsoredNetworksQuantity;

        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            this.myPosition = new Site(location.getLatitude(), location.getLongitude(), "", 0);
        }
        catch (SecurityException se) {
            this.myPosition = null;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView networkName = (TextView) rowView.findViewById(R.id.label);
        TextView networkRating = (TextView) rowView.findViewById(R.id.networkRating);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        LinearLayout networkBox = (LinearLayout) rowView.findViewById(R.id.networkBox);

        if ( networks[position].getSponsorshipStatus() == Network.Sponsorship.SPONSORED && position < sponsoredNetworksQuantity)
            networkBox.setBackgroundColor(Color.parseColor("#e1eaf3"));

        networkName.setText(networks[position].getName());
        imageView.setVisibility(View.GONE);
        try{
            networkRating.setText(this.calculateDistance(myPosition.calculateDistance(networks[position].getSite())));
        }
        catch (NullPointerException npe) {
            networkRating.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }

    private String calculateDistance(double value) {

        String networkDistance = new String();

        if ( value < 1000 )
            networkDistance = new Double(value).intValue() + " " + "m";

        else networkDistance = new String( ( new Double(value/1000).intValue() ) + " " + "Km");

        return networkDistance;
    }
}
