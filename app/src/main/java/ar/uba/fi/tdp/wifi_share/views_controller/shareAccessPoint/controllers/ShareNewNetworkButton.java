package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.UserInformationPersistence;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.NetworksFinderActivity;
import ar.uba.fi.tdp.wifi_share.model.NetworksDatabase;

public class ShareNewNetworkButton implements ActionButton {

    private NetworkCreator networkCreator;

    public ShareNewNetworkButton(Activity activity) {
        Button shareNewNetwork = (Button) activity.findViewById(R.id.shareNewNetworkButton);
        shareNewNetwork.setOnClickListener(this);

        networkCreator = new NetworkCreator(activity);
        UserInformationPersistence userInformationPersistence = new UserInformationPersistence(activity);
        userInformationPersistence.loadAuthenticationInformation();
        networkCreator.setNetworkOwner( userInformationPersistence.getUserID() );
    }

    public void onClick(View v) {
        addNetwork(v);
        returnMainActivity(v);
    }

    private void addNetwork(View v) {
        NetworksDatabase.accessDatabase().add(networkCreator.createNetwork());
    }

    private void returnMainActivity(View v){
        Intent myIntent = new Intent(v.getContext(), NetworksFinderActivity.class);
        v.getContext().startActivity(myIntent);
    }
}
