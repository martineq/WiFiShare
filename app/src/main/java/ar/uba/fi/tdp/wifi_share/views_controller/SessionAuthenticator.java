package ar.uba.fi.tdp.wifi_share.views_controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import ar.uba.fi.tdp.wifi_share.model.UserInformationPersistence;
import ar.uba.fi.tdp.wifi_share.views_controller.appLoader.StartupActivity;
import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.NetworksFinderActivity;

public class SessionAuthenticator {

    private Activity activity;
    private UserInformationPersistence userInformationPersistence;

    public SessionAuthenticator(Activity activity) {
        this.activity = activity;
        this.userInformationPersistence = new UserInformationPersistence(this.activity);
    }

    public boolean isUserAuthenticated() {
        return userInformationPersistence.isUserAuthenticated();
    }

    public void closeActualSession(Context context) {
        this.userInformationPersistence.removeUserInformation();
        this.selectNextActivity(context, StartupActivity.class);
    }

    public void loginSuccesfull(Context context) {
        this.selectNextActivity(context, NetworksFinderActivity.class);
    }

    private void selectNextActivity(Context context, Class classType) {
        Intent myIntent = new Intent(context, classType);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(myIntent);
    }

}
