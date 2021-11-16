package ar.uba.fi.tdp.wifi_share.model;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.Map;

public class UserInformationPersistence {

    private static String USER_PREFERENCES = "userPreferences";
    private static String USER_ID = "userID";
    private static String USER_PASSWORD = "userPassword";
    private static String USER_AUTHENTICATED = "userAuthenticated";

    private String userID;
    private String userPassword;

    private SharedPreferences sharedPreferences;

    public UserInformationPersistence(Activity activity) {

        this.sharedPreferences = activity.getSharedPreferences(this.USER_PREFERENCES, 0);

        if ( this.isUserAuthenticated() == false )
            this.removeUserInformation();
    }

    public void persistAuthenticationInformation(String userID, String userPassword) {

        this.userID = userID;
        this.userPassword = userPassword;

        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(this.USER_ID, this.userID);
        editor.putString(this.USER_PASSWORD, this.userPassword );
        editor.putBoolean(this.USER_AUTHENTICATED, true);
        editor.commit();
    }

    public boolean loadAuthenticationInformation() {

        userID = this.sharedPreferences.getString(this.USER_ID,"");
        userPassword = this.sharedPreferences.getString(this.USER_PASSWORD,"");

        return this.isUserAuthenticated();
    }

    public void removeUserInformation() {

        this.sharedPreferences.edit().remove(this.USER_ID);
        this.sharedPreferences.edit().remove(this.USER_PASSWORD);
        this.deauthenticateUser();
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    private void deauthenticateUser() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putBoolean(this.USER_AUTHENTICATED, false);
        editor.commit();
    }

    public boolean isUserAuthenticated() {
        return this.sharedPreferences.getBoolean(this.USER_AUTHENTICATED, false);
    }
}
