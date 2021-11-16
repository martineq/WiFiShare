package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.calificationDialog.CalificationDialog;

public class RankingButton implements View.OnClickListener {

    private Network network;
    private Button rankButton;
    private CalificationDialog dialog;

    public RankingButton(Activity activity, Network networkDetails) {
        this.network = networkDetails;

        this.rankButton = (Button) activity.findViewById(R.id.rankButton);
        this.rankButton.setOnClickListener(this);

        this.dialog = new CalificationDialog(activity);
    }

    public void onClick(View v) {
        this.dialog.constructDialog(network);
        this.dialog.showDialog();
    }

    public void setButtonEnabledState(boolean state) {
        rankButton.setEnabled(state);
    }
}
