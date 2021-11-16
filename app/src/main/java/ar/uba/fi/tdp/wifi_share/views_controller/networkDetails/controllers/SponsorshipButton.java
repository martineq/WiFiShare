package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.model.NetworksDatabase;

public class SponsorshipButton implements View.OnClickListener {

    private Network network;
    private Button sponsorshipButton;
    private Network.Sponsorship onClickSponsorshipType;

    public SponsorshipButton(Activity activity, Network networkDetails) {
        this.network = networkDetails;

        this.sponsorshipButton = (Button) activity.findViewById(R.id.rankButton);
        this.setButtonText();
        this.sponsorshipButton.setOnClickListener(this);
    }

    private void setButtonText(){

        if ( this.network.getSponsorshipStatus() == Network.Sponsorship.SPONSORED ) {
            this.sponsorshipButton.setText(R.string.unsponsor);
            this.onClickSponsorshipType = Network.Sponsorship.UNSPONSORED;
        }

        else {
            this.sponsorshipButton.setText(R.string.sponsor);
            this.onClickSponsorshipType = Network.Sponsorship.SPONSORED;
        }
    }

    public void onClick(View v) {

        Network modifiedNetwork = new Network(this.network.getName(), this.network.getMac());
        modifiedNetwork.setSponsorshipStatus(onClickSponsorshipType);

        NetworksDatabase.accessDatabase().update(this.network, modifiedNetwork);
        this.network = NetworksDatabase.accessDatabase().findNetwork(this.network.getMac());
        this.setButtonText();
    }
}
