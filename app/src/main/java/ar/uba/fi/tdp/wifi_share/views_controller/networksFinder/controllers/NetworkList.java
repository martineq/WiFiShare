package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.NetworkDetailsActivity;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.ShareAccessPointActivity;

public class NetworkList implements AdapterView.OnItemClickListener {

    private ListView networksList;
    private AccessPoints accessPoints;
    private Activity activity;

    public NetworkList(Activity activity) {

        networksList = (ListView) activity.findViewById(R.id.listaRedes);
        networksList.setOnItemClickListener(this);

        this.activity = activity;
    }

    public void modifyAccessPointList(AccessPoints accessPoints, NetworkPrioritizer.NetworkPrioritzationType type) {
        this.accessPoints = accessPoints;

        if ( type.equals(NetworkPrioritizer.NetworkPrioritzationType.PRIORITIZE_CALIFICATION))
            networksList.setAdapter(accessPoints.createRatingAdapter(activity));

        if ( type.equals(NetworkPrioritizer.NetworkPrioritzationType.PRIORITIZE_PROXIMITY))
            networksList.setAdapter(accessPoints.createDistanceAdapter(activity));
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Network network = (Network) parent.getItemAtPosition(position);
        Intent myIntent = new Intent(activity, NetworkDetailsActivity.class);
        myIntent.putExtra("Network", network);
        activity.startActivity(myIntent);
    }
}
