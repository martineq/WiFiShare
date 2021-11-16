package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.model.NetworksDatabase;
import ar.uba.fi.tdp.wifi_share.model.Site;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.NetworksFinderActivity;

public class ModifyNetworkButton implements ActionButton {

    private Button modifyNetworkButton;
    private Activity activity;
    private Network currentNetwork;

    public ModifyNetworkButton(Activity activity, Network network) {

        this.modifyNetworkButton = (Button) activity.findViewById(R.id.shareNewNetworkButton);
        this.modifyNetworkButton.setOnClickListener(this);
        this.modifyNetworkButton.setText(R.string.modify_button);
        this.currentNetwork = network;
        this.activity = activity;
    }

    public void onClick(View v) {
        modifyNetwork(v);
        returnMainActivity(v);
    }

    private void modifyNetwork(View v) {
        Network network = new Network(this.loadEditTextInformation(R.id.addNetworkName), this.loadEditTextInformation(R.id.addMACAdressID));
        network.setPassword(this.loadEditTextInformation(R.id.addNetworkPassword));
        network.setSite(new LoadSiteDetails(this.activity).loadSiteInformation());

        NetworksDatabase.accessDatabase().update(this.currentNetwork, network);
    }

    private void returnMainActivity(View v){
        Intent myIntent = new Intent(v.getContext(), NetworksFinderActivity.class);
        v.getContext().startActivity(myIntent);
    }

    private String loadEditTextInformation(int id) {
        return ((EditText) this.activity.findViewById(id)).getText().toString();
    }
}
