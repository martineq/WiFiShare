package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers;

import android.app.Activity;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import ar.uba.fi.tdp.wifi_share.R;

public class ShareWiFiInformation implements CompoundButton.OnCheckedChangeListener {

    private Switch wifiSwitch;
    private Activity activity;
    private WifiManager wifiManager;

    public ShareWiFiInformation(Activity activity) {
        this.wifiSwitch = (Switch) activity.findViewById(R.id.wifiInformationSwich);
        this.activity = activity;
        this.wifiSwitch.setOnCheckedChangeListener(this);
    }

    private void loadInformation() {

        this.wifiManager = (WifiManager) activity.getSystemService(activity.WIFI_SERVICE);
        WifiInfo wifiConnection = wifiManager.getConnectionInfo();
        if ( wifiConnection.getBSSID() != null ) {
            this.loadEditText(R.id.addNetworkName, this.obtainNetworkName(wifiConnection.getSSID()));
            this.loadEditText(R.id.addMACAdressID, wifiConnection.getBSSID());
        }
        else {
            this.wifiSwitch.toggle();
        }
    }

    private String obtainNetworkName(String SSID) {
        int end = SSID.length() - 1;
        return SSID.substring(1, end);
    }

    private void blockEditText(boolean blockedState) {
        this.changeEditTextState(R.id.addNetworkName, blockedState);
        this.changeEditTextState(R.id.addMACAdressID, blockedState);
    }

    private void changeEditTextState(int id, boolean state) {
        ((EditText) activity.findViewById(id)).setEnabled(state);
    }

    private void loadEditText(int editTextID, String information) {
        ((EditText) activity.findViewById(editTextID)).setText(information);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        try {
            if ( isChecked == true ) {
                this.loadInformation();
                this.blockEditText(false);
            }
            else
                this.blockEditText(true);

        } catch (NullPointerException npe) {}

    }
}
