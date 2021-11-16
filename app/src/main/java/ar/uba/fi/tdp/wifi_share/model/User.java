package ar.uba.fi.tdp.wifi_share.model;

import java.util.Set;

public class User {

    private String user;
    private String password;
    private boolean administrativePrivileges;

    private Set<Network> networks;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
        this.administrativePrivileges = false;
    }

    public void addNetwork(Network network) {
        networks.add(network);
    }

    public void removeNetwork(Network network) {
        networks.remove(network);
    }

    public String getID() {
        return user;
    }

    public void setID(String id){
        this.user = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Network> getNetworks() {
        return this.networks;
    }

    public void setNetworks(Set<Network> networks) {
        this.networks = networks;
    }

    public boolean authenticateUser(User other) {
        return ( this.user.equals(other.user) && this.password.equals(other.password) );
    }

    public boolean hasAdministrativePrivileges() {
        return administrativePrivileges;
    }

    public void setAdministrativePrivileges(boolean administrativePrivileges) {
        this.administrativePrivileges = administrativePrivileges;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if ( object.getClass() == this.getClass() ) {

            User other = (User) object;

            if ( this.user.equals(other.user) && this.password.equals(other.password) && ( this.administrativePrivileges == other.administrativePrivileges ) )
                result = true;
        }

        return result;
    }

    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (administrativePrivileges ? 1 : 0);
        result = 31 * result + (networks != null ? networks.hashCode() : 0);
        return result;
    }
}
