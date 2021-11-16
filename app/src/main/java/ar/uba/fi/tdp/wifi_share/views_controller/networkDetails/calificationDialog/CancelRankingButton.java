package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.calificationDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import ar.uba.fi.tdp.wifi_share.R;

public class CancelRankingButton implements View.OnClickListener {

    private AlertDialog alertDialog;
    private Button cancel;

    public CancelRankingButton(Activity activity, View dialog) {
        this.cancel = (Button) dialog.findViewById(R.id.cancelRankingButton);
        this.cancel.setOnClickListener(this);
    }

    public void onClick(View v) {
        this.alertDialog.dismiss();
    }

    public void linkDialogBox(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }
}
