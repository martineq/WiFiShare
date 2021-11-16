package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.NetworksFinderActivity;
import ar.uba.fi.tdp.wifi_share.model.Network;

public class ConnectNetworkButton implements View.OnClickListener {

    private Network network;
    private Button connectNetwork;
    private ConnectionFacilitator connectionFacilitator;
    public ConnectNetworkButton(Activity activity, Network networkDetails) {
        this.network = networkDetails;

        this.connectNetwork = (Button) activity.findViewById(R.id.connectButton);
        this.connectNetwork.setOnClickListener(this);
        this.connectionFacilitator = new ConnectionFacilitator(activity);
    }

    public void onClick(View v) {
        connectionFacilitator.connect(network);
        returnMainActivity(v);
    }

    private void returnMainActivity(View v){
        Intent myIntent = new Intent(v.getContext(), NetworksFinderActivity.class);
        v.getContext().startActivity(myIntent);
    }

    public void setButtonEnabledState(boolean state) {
        connectNetwork.setEnabled(state);
    }
}
