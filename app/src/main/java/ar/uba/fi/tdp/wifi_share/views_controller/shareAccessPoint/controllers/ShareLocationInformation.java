package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.io.IOException;
import java.util.List;

import ar.uba.fi.tdp.wifi_share.R;

public class ShareLocationInformation implements CompoundButton.OnCheckedChangeListener {

    private Switch locationSwitch;
    private Activity activity;
    private LocationManager locationManager;
    private Geocoder geocoder;

    public ShareLocationInformation(Activity activity) {

        this.locationSwitch = (Switch) activity.findViewById(R.id.locationInformationSwich);
        this.activity = activity;
        this.locationSwitch.setOnCheckedChangeListener(this);
        this.locationManager = (LocationManager) activity.getSystemService(activity.LOCATION_SERVICE);
        geocoder = new Geocoder(activity);
    }

    private void loadInformation() {

        try {
            Location location = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            this.loadEditText(R.id.addLatitudeID, new Double(location.getLatitude()).toString());
            this.loadEditText(R.id.addLongitudeID, new Double(location.getLongitude()).toString());
            this.loadEditText(R.id.addNetworkAddressID,addresses.get(0).getThoroughfare());
            this.loadEditText(R.id.addNetworkAddressNumberID,addresses.get(0).getFeatureName());
            this.loadEditText(R.id.addNetworkCityID, addresses.get(0).getAdminArea());

        } catch (SecurityException se) {
        } catch (IOException ioe) {
            try {
                Location location = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                this.loadEditText(R.id.addLatitudeID, new Double(location.getLongitude()).toString());
                this.loadEditText(R.id.addLongitudeID, new Double(location.getLatitude()).toString());
            } catch (SecurityException se) {
            }
        }
    }

    private void blockEditText(boolean blockedState) {
        this.changeEditTextState(R.id.addLatitudeID, blockedState);
        this.changeEditTextState(R.id.addLongitudeID, blockedState);
        this.changeEditTextState(R.id.addNetworkCityID, blockedState);
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

        } catch (NullPointerException npe) {

            this.locationSwitch.setChecked(false);
            this.locationSwitch.setEnabled(false);
        }

    }
}
