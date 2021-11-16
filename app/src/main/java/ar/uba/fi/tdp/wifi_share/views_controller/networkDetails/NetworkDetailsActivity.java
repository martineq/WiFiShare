package ar.uba.fi.tdp.wifi_share.views_controller.networkDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers.AccessPointValidator;
import ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers.ActivityDisplayedInformationSwitcher;
import ar.uba.fi.tdp.wifi_share.views_controller.networkDetails.controllers.ConnectNetworkButton;
import ar.uba.fi.tdp.wifi_share.model.Network;
import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers.AccessPointFinder;

public class NetworkDetailsActivity extends AppCompatActivity {
    private AccessPointValidator accessPointValidator;
    private ConnectNetworkButton connectNetworkButton;
    private Network network;
    private ActivityDisplayedInformationSwitcher activityDisplayedInformationSwitcher;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_description);

        this.network = (Network) getIntent().getSerializableExtra("Network");
        this.accessPointValidator = new AccessPointValidator(new AccessPointFinder(this));

        this.connectNetworkButton = new ConnectNetworkButton(this, network);
        this.activityDisplayedInformationSwitcher = new ActivityDisplayedInformationSwitcher(this, network);

        this.updateNetworkSignalIntensity();
        this.updateNetworkCipherType();
        this.verifyNetworkAvailability();

        this.loadAllTextView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void verifyNetworkAvailability() {
        connectNetworkButton.setButtonEnabledState(accessPointValidator.isNetworkAvailable(network));
    }

    private void updateNetworkCipherType() {
        network.setCapabilities(accessPointValidator.getCypherMethod(network));
    }

    private void updateNetworkSignalIntensity() {
        network.setSignalStrengh(accessPointValidator.getSignalStrenght(network));
    }

    private void loadAllTextView() {
        this.setTextViewInformation(R.id.networkNameID, network.getName());
        this.setTextViewInformation(R.id.networkAddressID, new String( network.getSite().getAddress() + " " + network.getSite().getAddressNumber()));
        this.setTextViewInformation(R.id.networkRankingID, network.getRanking().toString());
        this.setTextViewInformation(R.id.signalStrenghtID, network.getSignalStrengh().toString());
        this.setTextViewInformation(R.id.networkCypherID, network.getCapabilities());
    }

    private void setTextViewInformation(int textViewID, String information) {
        ((TextView) findViewById(textViewID)).setText(information);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.network_menu, menu);
        this.activityDisplayedInformationSwitcher.selectMenuOptions(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if ( this.activityDisplayedInformationSwitcher.menuItemSelected(item) == true )
            return true;

        return super.onOptionsItemSelected(item);
    }
}
