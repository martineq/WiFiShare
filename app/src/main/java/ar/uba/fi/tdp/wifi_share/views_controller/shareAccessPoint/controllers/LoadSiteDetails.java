package ar.uba.fi.tdp.wifi_share.views_controller.shareAccessPoint.controllers;

import android.app.Activity;
import android.text.Editable;
import android.widget.EditText;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Site;

public class LoadSiteDetails {

    private Activity activity;

    public LoadSiteDetails(Activity activity) {
        this.activity = activity;
    }

    public Site loadSiteInformation() {

        String city = this.getStringFromEditText(R.id.addNetworkCityID);
        String address = this.getStringFromEditText(R.id.addNetworkAddressID);
        int addressNumber = this.loadIntFromEditText(R.id.addNetworkAddressNumberID);
        double longitude = this.loadDoubleFromEditText(R.id.addLongitudeID);
        double latitude = this.loadDoubleFromEditText(R.id.addLatitudeID);

        Site site = new Site(latitude, longitude, address, addressNumber);
        site.setCity(city);

        return site;
    }

    private Double loadDoubleFromEditText(int editText) {
        try {
            return new Double(this.getStringFromEditText(editText));
        } catch (NumberFormatException nfe ) {
            return Site.INVALID_LONGITUDE;
        }
    }

    private Integer loadIntFromEditText(int editText) {
        try{
            return new Integer(this.getStringFromEditText(editText));
        } catch (NumberFormatException nfe) {
            return Site.INVALID_ADDRESS_NUMBER;
        }
    }

    private String getStringFromEditText(int editText) {
        return (((EditText) this.activity.findViewById(editText)).getText()).toString();
    }
}
