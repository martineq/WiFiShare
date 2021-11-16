package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers;

import android.app.Activity;
import android.widget.EditText;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.model.Site;

public class NetworkDetailsLoader {

    private Network network;
    private Activity activity;

    public NetworkDetailsLoader(Activity activity, Network network) {
        this.activity = activity;
        this.network = network;
    }

    public void load() {
        activity.setTitle(R.string.modify_my_network);

        this.loadEditTextInformation(R.id.addNetworkName, network.getName());
        this.loadEditTextInformation(R.id.addNetworkPassword, network.getPassword());
        this.loadEditTextInformation(R.id.addMACAdressID, network.getMac());
        this.loadSiteInformation(this.network.getSite());
    }

    private void loadEditTextInformation(int id, String information) {
        ((EditText) activity.findViewById(id)).setText(information);
    }

    private void loadSiteInformation(Site site) {
        this.loadEditTextInformation(R.id.addNetworkAddressID, site.getAddress());
        this.loadEditTextInformation(R.id.addNetworkAddressNumberID, new Integer(site.getAddressNumber()).toString());
        this.loadEditTextInformation(R.id.addLongitudeID, new Double(site.getLongitude()).toString());
        this.loadEditTextInformation(R.id.addLatitudeID, new Double(site.getLatitude()).toString());
        this.loadEditTextInformation(R.id.addNetworkCityID, site.getCity());
    }

}
