package ar.uba.fi.tdp.wifi_share.model;

import java.util.HashMap;
import java.util.Map;

public class UsersDatabase {

    private String ADMIN_USER = "admin";

    private Map<String,User> validUsers;

    private void generateValidUsers() {

        String []users= { "martin", "uriel", "cesar", "nicolas", "pablo", "martineq",ADMIN_USER};
        String []passwords= {"85780", "93252", "81052","87212","87286","86012","fiuba"};

        for(int i = 0; i < users.length; ++i) {
            this.validUsers.put(users[i], new User(users[i], passwords[i]));
        }

        this.concedeAdministrativePrivileges();
    }

    public UsersDatabase() {
        this.validUsers = new HashMap<>();
        this.generateValidUsers();
    }

    public boolean isAnAdminUser(User user) {
        try {
            return this.validUsers.get(user.getID()).hasAdministrativePrivileges();
        }

        catch (NullPointerException userNotExist) {
            return false;
        }
    }

    public boolean existsUser(User user) {
        return this.validUsers.containsKey(user.getID());
    }

    public boolean isValidUser(User user) {
        try {
            User remoteUserInformation = this.validUsers.get(user.getID());
            return remoteUserInformation.authenticateUser(user);
        }

        catch (NullPointerException npe) {
            return false;
        }
    }

    public void fetchUserInformation(User user) {

        User remoteUserData = this.validUsers.get(user.getID());

        if ( remoteUserData == null ) {
            throw new InvalidUserException();
        }

        user.setID(remoteUserData.getID());
        user.setPassword(remoteUserData.getPassword());
        user.setNetworks(remoteUserData.getNetworks());

    }

    public void updateUserInformation(User user) {

        User remoteUserData = this.validUsers.get(user.getID());

        if ( remoteUserData == null ) {
            throw new InvalidUserException();
        }

        remoteUserData.setPassword(user.getPassword());
        remoteUserData.setNetworks(user.getNetworks());

    }

    public void addNewUser(User user) {

        if ( this.existsUser(user) == true )

            throw new InvalidUserException();

        this.validUsers.put(user.getID(), user);
    }

    private void concedeAdministrativePrivileges() {
        try {
            User user = this.validUsers.get(ADMIN_USER);
            user.setAdministrativePrivileges(true);
            this.validUsers.put(user.getID(), user);
        }

        catch (NullPointerException npe) {}
    }

}
