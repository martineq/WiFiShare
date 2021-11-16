package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.calificationDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.model.UserInformationPersistence;

public class CalificationDialog {

    private AcceptRankingButton accept;
    private CancelRankingButton reject;
    private AlertDialog.Builder builder;
    private Activity activity;

    public CalificationDialog(Activity activity) {
        this.activity = activity;
    }

    public void constructDialog(Network networkDetails) {
        this.builder = new AlertDialog.Builder(activity);
        builder.setTitle("Califique la conexion");
        View layout = activity.getLayoutInflater().inflate(R.layout.calificate_connection, null);
        this.builder.setView(layout);

        this.accept = new AcceptRankingButton(layout, networkDetails, this.activity);
        this.accept.setUserInformationPersistence(new UserInformationPersistence(activity));
        this.reject = new CancelRankingButton(activity, layout);
    }

    public void showDialog() {
        AlertDialog alertDialog = builder.create();
        this.accept.linkDialogBox(alertDialog);
        this.reject.linkDialogBox(alertDialog);
        alertDialog.show();
    }
}
