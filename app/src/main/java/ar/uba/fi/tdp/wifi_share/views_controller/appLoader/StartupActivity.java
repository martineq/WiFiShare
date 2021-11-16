package ar.uba.fi.tdp.wifi_share.views_controller.appLoader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.controllers.WiFiStatusController;
import ar.uba.fi.tdp.wifi_share.views_controller.SessionAuthenticator;
import ar.uba.fi.tdp.wifi_share.views_controller.appLoader.controllers.LoginButton;

public class StartupActivity extends AppCompatActivity {

    private WiFiStatusController wiFiStatusController;
    private LoginButton loginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        this.setTitle(R.string.login);

        wiFiStatusController = new WiFiStatusController(this);
        wiFiStatusController.activateWifi();

        this.userWasAuthenticated();
    }

    private void userWasAuthenticated() {
        SessionAuthenticator sessionAuthenticator = new SessionAuthenticator(this);

        if ( sessionAuthenticator.isUserAuthenticated() == true )
            sessionAuthenticator.loginSuccesfull(this);

        else
            this.loginButton = new LoginButton(this);
    }
}
