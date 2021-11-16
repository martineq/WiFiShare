package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers.ActionButton;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers.CancelShareNewNetworkButton;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers.ModifyNetworkButton;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers.NetworkDetailsLoader;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers.ShareLocationInformation;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers.ShareNewNetworkButton;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers.ShareWiFiInformation;

public class ShareAccessPointActivity extends AppCompatActivity {

    private ActionButton actionButton;
    private CancelShareNewNetworkButton cancelShareNewNetworkButton;
    private ShareWiFiInformation shareWiFiInformation;
    private ShareLocationInformation shareLocationInformation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_access_point);

        Network network =  (Network) getIntent().getSerializableExtra("Network");

        if ( network != null ) {
            new NetworkDetailsLoader(this, network).load();
            actionButton = new ModifyNetworkButton(this, network);
        }

        else
            actionButton = new ShareNewNetworkButton(this);

        cancelShareNewNetworkButton = new CancelShareNewNetworkButton(this);
        shareWiFiInformation = new ShareWiFiInformation(this);
        shareLocationInformation = new ShareLocationInformation(this);
    }
}
