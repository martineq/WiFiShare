package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.controllers.UserInformationKeeper;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.model.NetworksDatabase;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.NetworksFinderActivity;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.ShareAccessPointActivity;

public class ActivityDisplayedInformationSwitcher {

    private String userID;
    private boolean privilegedUser;
    private boolean networkOwnership;
    private View.OnClickListener buttonListener;
    private Activity activity;
    private Network network;

    public ActivityDisplayedInformationSwitcher(Activity activity, Network network) {

        UserInformationKeeper userInformationKeeper = UserInformationKeeper.accessUserInformationKeeper(activity);

        userID = userInformationKeeper.getUserID();
        privilegedUser = userInformationKeeper.hasAdministrativesPrivileges();
        networkOwnership = network.getNetworkOwner().equals(userID);

        this.instantiateListener(activity, network);
        this.activity = activity;
        this.network = network;
    }

    public void instantiateListener(Activity activity, Network network) {
        if ( privilegedUser == true )
            this.buttonListener = new SponsorshipButton(activity, network);

        else this.buttonListener = new RankingButton(activity, network);
    }

    public void selectMenuOptions(Menu menu) {

        MenuItem modifyNetwork = menu.findItem(R.id.modifyNetwork);
        MenuItem deleteNetwork = menu.findItem(R.id.deleteNetwork);

        if ( this.networkOwnership == false && this.privilegedUser == false) {
            modifyNetwork.setVisible(false);
            deleteNetwork.setVisible(false);
        }

    }

    public boolean menuItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.modifyNetwork) {
            Intent myIntent = new Intent(this.activity, ShareAccessPointActivity.class);
            myIntent.putExtra("Network", network);
            this.activity.startActivity(myIntent);
            return true;
        }

        if ( id == R.id.deleteNetwork) {
            NetworksDatabase.accessDatabase().remove(network);
            Intent myIntent = new Intent(this.activity, NetworksFinderActivity.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.activity.startActivity(myIntent);
            return true;
        }

        return false;
    }
}
