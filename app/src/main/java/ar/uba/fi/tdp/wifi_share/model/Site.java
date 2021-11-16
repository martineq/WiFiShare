package ar.uba.fi.tdp.wifi_share.model;

import android.location.Location;

import java.io.Serializable;

public class Site implements Serializable {

    public static Double INVALID_LATITUDE = 256.0;
    public static Double INVALID_LONGITUDE = 256.0;
    public static int INVALID_ADDRESS_NUMBER = -1;

    private double latitude;
    private double longitude;
    private String address;
    private int addressNumber;
    private String city;

    public Site(double latitude, double longitude, String addressName, int addressNumber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = addressName;
        this.addressNumber = addressNumber;
        this.city = new String();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public double calculateDistance(Site foreignSite) {
        float results[] = new float[10];
        Location.distanceBetween(this.latitude, this.longitude, foreignSite.latitude, foreignSite.longitude, results);

        return results[0];
    }

    public void update(Site site) {
        this.address = this.attributeHasChanged(this.address, site.address);
        this.city = this.attributeHasChanged(this.city, site.city);
        this.updateSiteNumberInformation(site);
    }

    private String attributeHasChanged(String oldValue, String newValue) {
        String attribute = oldValue;

        if ( newValue.length() != 0 && oldValue.equals(newValue) == false )
            attribute = newValue;

        return attribute;
    }

    private void updateSiteNumberInformation(Site site) {

        if ( site.latitude != Site.INVALID_LATITUDE )
            this.latitude = site.latitude;

        if ( site.longitude != Site.INVALID_LONGITUDE )
            this.longitude = site.longitude;

        if ( site.addressNumber != Site.INVALID_ADDRESS_NUMBER )
            this.addressNumber = site.addressNumber;
    }
}
