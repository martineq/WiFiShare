package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.NetworksFinderActivity;

public class CancelShareNewNetworkButton implements View.OnClickListener {

    public CancelShareNewNetworkButton(Activity activity) {
        Button shareNewNetwork = (Button) activity.findViewById(R.id.skipAddNetworkButton);
        shareNewNetwork.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent myIntent = new Intent(v.getContext(), NetworksFinderActivity.class);
        v.getContext().startActivity(myIntent);
    }
}
