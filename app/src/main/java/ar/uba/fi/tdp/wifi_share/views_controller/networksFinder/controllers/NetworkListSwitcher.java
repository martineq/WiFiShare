package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.CompoundButton;
import android.widget.Switch;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.NetworksDatabase;

public class NetworkListSwitcher implements CompoundButton.OnCheckedChangeListener {

    private Switch switchSolverType;
    private NetworkPrioritizer networkPrioritizer;
    private NetworkList networkList;
    private int sponsoredNetworksQuantity = 2;
    private boolean switchStatus;

    public NetworkListSwitcher(Activity activity) {

        switchSolverType = (Switch) activity.findViewById(R.id.switchSolver);
        switchSolverType.setChecked(false);
        switchSolverType.setTextOn("   ");
        switchSolverType.setTextOff("   ");

        StateListDrawable thumbStates = new StateListDrawable();
        thumbStates.addState(new int[]{android.R.attr.state_checked}, new ColorDrawable(Color.GRAY));
        thumbStates.addState(new int[]{-android.R.attr.state_enabled}, new ColorDrawable(Color.GRAY));
        switchSolverType.setThumbDrawable(thumbStates);

        this.networkPrioritizer = new NetworkPrioritizer(activity);
        this.networkList = new NetworkList(activity);

        switchSolverType.setOnCheckedChangeListener(this);

        switchStatus = false;
        this.updateNetworkList();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        this.switchStatus = isChecked;
        this.updateNetworkList();
    }

    public void updateNetworkList() {

        if ( switchStatus == true ) {
            networkPrioritizer.changeCurrentPriorityType(NetworkPrioritizer.NetworkPrioritzationType.PRIORITIZE_CALIFICATION);
            AccessPoints accessPoints = networkPrioritizer.getAccessPointList(NetworksDatabase.accessDatabase());
            accessPoints.setSponsoredNetworksQuantity(sponsoredNetworksQuantity);
            accessPoints.rearrangeSponsoredNetworks();
            networkList.modifyAccessPointList(accessPoints, NetworkPrioritizer.NetworkPrioritzationType.PRIORITIZE_CALIFICATION);
        }
        else {
            networkPrioritizer.changeCurrentPriorityType(NetworkPrioritizer.NetworkPrioritzationType.PRIORITIZE_PROXIMITY);
            AccessPoints accessPoints = networkPrioritizer.getAccessPointList(NetworksDatabase.accessDatabase());
            accessPoints.setSponsoredNetworksQuantity(sponsoredNetworksQuantity);
            accessPoints.rearrangeSponsoredNetworks();
            networkList.modifyAccessPointList(accessPoints, NetworkPrioritizer.NetworkPrioritzationType.PRIORITIZE_PROXIMITY);
        }
    }
}
