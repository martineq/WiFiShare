package ar.uba.fi.tdp.wifi_share.controllers;

import android.app.Activity;

import ar.uba.fi.tdp.wifi_share.model.User;
import ar.uba.fi.tdp.wifi_share.model.UserInformationPersistence;
import ar.uba.fi.tdp.wifi_share.model.UsersDatabase;

public class UserInformationKeeper {

    private static User user;
    private static UserInformationKeeper userInformationKeeper = null;

    public static UserInformationKeeper accessUserInformationKeeper(Activity activity) {

        if ( userInformationKeeper == null )
            userInformationKeeper = new UserInformationKeeper(activity);

        return userInformationKeeper;
    }

    private UserInformationKeeper(Activity activity) {
        UserInformationPersistence userInformation = new UserInformationPersistence(activity);
        userInformation.loadAuthenticationInformation();

        user = new User(userInformation.getUserID(), userInformation.getUserPassword());

        UsersDatabase usersDatabase = new UsersDatabase();
        user.setAdministrativePrivileges( usersDatabase.isAnAdminUser(user) );
    }

    public void updateUserInformation(Activity activity) {
        userInformationKeeper = new UserInformationKeeper(activity);
    }

    public String getUserID() {
        return user.getID();
    }

    public boolean hasAdministrativesPrivileges() {
        return user.hasAdministrativePrivileges();
    }
}
