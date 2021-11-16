package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import ar.uba.fi.tdp.wifi_share.model.NetworksDatabase;
import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.views_controller.SessionAuthenticator;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers.ConnectionListener;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers.NetworkListSwitcher;
import ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.ShareAccessPointActivity;

public class NetworksFinderActivity extends AppCompatActivity implements NetworkStatusListener {

    private TextView connectedStatus;
    private ConnectionListener connectionListener;
    private NetworkListSwitcher networkListSwitcher;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redes);

        NetworksDatabase.createDatabase();
        this.networkListSwitcher = new NetworkListSwitcher(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        connectedStatus = (TextView) findViewById(R.id.networkStatus);
        this.modifyNetworkStatusVisibility();

        connectionListener = new ConnectionListener(this);
        this.registerReceiver(connectionListener, new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION));
        // this.registerReceiver(connectionListener, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }

    public void onResume() {
        super.onResume();
        networkListSwitcher.updateNetworkList();
    }

    private void modifyNetworkStatusVisibility(){
        ViewGroup.LayoutParams params = connectedStatus.getLayoutParams();
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        String networkMAC = wifiManager.getConnectionInfo().getBSSID();

        if ( wifiManager.getConnectionInfo().getSupplicantState() == SupplicantState.COMPLETED &&
                NetworksDatabase.accessDatabase().isNetworkAvailable(networkMAC) ) {
            params.height = 70;
        }

        else params.height = 0;

        connectedStatus.setLayoutParams(params);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_redes, menu);
        return true;
    }

    public void statusHasChanged() {
        this.modifyNetworkStatusVisibility();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_AP) {
            Intent myIntent = new Intent(this, ShareAccessPointActivity.class);
            this.startActivity(myIntent);
            return true;
        }

        if ( id == R.id.logout) {
            new SessionAuthenticator(this).closeActualSession(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
