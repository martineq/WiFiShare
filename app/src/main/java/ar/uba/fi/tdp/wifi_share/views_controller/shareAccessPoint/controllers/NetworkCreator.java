package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers;

import android.app.Activity;
import android.widget.EditText;

import ar.uba.fi.tdp.wifi_share.model.Site;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.R;

public class NetworkCreator {

    private Activity activity;
    private Network network;

    public String getNetworkOwner() {
        return networkOwner;
    }

    public void setNetworkOwner(String networkOwner) {
        this.networkOwner = networkOwner;
    }

    private String networkOwner;

    //TODO
    private static int fakeMac = 0;

    public NetworkCreator(Activity activity){
        this.activity = activity;
        this.networkOwner = "";
    }

    public Network createNetwork(){
        String MACaddress = this.getInformation(R.id.addMACAdressID);

        if ( MACaddress.length() == 0 )
            MACaddress = new Integer(fakeMac++).toString();

        this.network = new Network( this.getInformation(R.id.addNetworkName), MACaddress );
        this.network.setPassword(this.getInformation(R.id.addNetworkPassword));
        this.network.setNetworkOwner(this.networkOwner);

        this.network.setSite(new LoadSiteDetails(this.activity).loadSiteInformation());

        return network;
    }

    private String getInformation(int id) {
        return ((EditText) activity.findViewById(id)).getText().toString();
    }

}
