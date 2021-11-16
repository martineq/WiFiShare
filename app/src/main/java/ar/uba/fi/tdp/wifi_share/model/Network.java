package ar.uba.fi.tdp.wifi_share.model;

import java.io.Serializable;

public class Network implements Serializable {

    public static enum Sponsorship { SPONSORED, UNSPONSORED, UNESTABLISHED };

    private String name;
    private String password;
    private String networkOwner;

    private int ranking;
    private Sponsorship sponsorshipStatus;
    private Site site;

    private String mac;
    private String capabilities;
    private int signalStrengh;

    public Network(String name, String mac) {
        this.name = name;
        this.password = new String();
        this.networkOwner = new String();
        this.ranking = -1;
        this.sponsorshipStatus = Sponsorship.UNESTABLISHED;
        this.site = new Site(Site.INVALID_LATITUDE, Site.INVALID_LONGITUDE, "", Site.INVALID_ADDRESS_NUMBER);
        this.mac = mac;
        this.capabilities = new String();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Integer getRanking(){
        return this.ranking;
    }

    public void setSignalStrengh(int signalStrengh) {
        this.signalStrengh = signalStrengh;
    }

    public Integer getSignalStrengh() {
        return this.signalStrengh;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getCapabilities(){
        return this.capabilities;
    }

    public void setMAC(String mac){
        this.mac = mac;
    }

    public String getMac() {
        return this.mac;
    }

    public void setNetworkOwner(String user) {
        this.networkOwner = user;
    }

    public String getNetworkOwner() {
        return this.networkOwner;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Sponsorship getSponsorshipStatus() {
        return sponsorshipStatus;
    }

    public void setSponsorshipStatus(Sponsorship newSponsorshipStatus) {
        this.sponsorshipStatus = newSponsorshipStatus;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public Network update(Network network) {

        this.name = this.attributeHasChanged(this.name, network.name);
        this.password = this.attributeHasChanged(this.password, network.password);
        this.mac = this.attributeHasChanged(this.mac, network.mac);

        this.site.update(network.getSite());

        this.networkOwner = this.attributeHasChanged(this.networkOwner, network.networkOwner);
        this.capabilities = this.attributeHasChanged(this.capabilities, network.capabilities);
        this.modifyRanking(network);
        this.sponsorshipStatus = getUpdatedSponsorship(this.sponsorshipStatus, network.sponsorshipStatus);

        return this;
    }

    private Sponsorship getUpdatedSponsorship(Sponsorship oldSponsorshipType, Sponsorship newSponsorshipType ) {

        Sponsorship sponsorship = oldSponsorshipType;

        if ( newSponsorshipType != Sponsorship.UNESTABLISHED )
            sponsorship = newSponsorshipType;

        return sponsorship;
    }

    private String attributeHasChanged(String oldValue, String newValue) {

        String attribute = oldValue;

        if ( newValue.length() != 0 && oldValue.equals(newValue) == false )
            attribute = newValue;

        return attribute;
    }

    private void modifyRanking(Network network) {

        if ( network.ranking >= 0 )
            this.ranking = network.ranking;
    }
}
