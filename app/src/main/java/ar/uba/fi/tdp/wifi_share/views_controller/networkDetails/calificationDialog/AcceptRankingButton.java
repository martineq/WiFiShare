package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.calificationDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.model.NetworksDatabase;
import ar.uba.fi.tdp.wifi_share.model.UserInformationPersistence;

public class AcceptRankingButton implements View.OnClickListener {

    private Network network;
    private Button accept;
    private RatingBar ratingBar;
    private AlertDialog alertDialog;
    private UserInformationPersistence userInformationPersistence;
    private Activity context;

    public AcceptRankingButton(View dialog, Network networkDetails, Activity context) {
        this.context = context;

        this.network = networkDetails;

        this.accept = (Button) dialog.findViewById(R.id.acceptRankingButton);
        this.accept.setOnClickListener(this);

        this.ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
        // this.ratingBar.setOnClickListener(this);
    }

    public void setUserInformationPersistence(UserInformationPersistence user) {
        this.userInformationPersistence = user;
    }

    public void onClick(View v) {
        userInformationPersistence.loadAuthenticationInformation();
        String userID = userInformationPersistence.getUserID();
        String userPassword = userInformationPersistence.getUserPassword();

        NetworksDatabase networksDatabase = NetworksDatabase.accessDatabase();
        Network currentNetwork = networksDatabase.findNetwork(this.network.getMac());

        int rank = Float.valueOf(this.ratingBar.getRating()).intValue();
        currentNetwork.setRanking(rank);
        TextView ranking = (TextView) context.findViewById(R.id.networkRankingID);
        ranking.setText(String.valueOf(rank));

        alertDialog.dismiss();
    }

    public void linkDialogBox(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    public void setButtonEnabledState(boolean state) {
        accept.setEnabled(state);
    }
}
