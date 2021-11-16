package ar.uba.fi.tdp.wifi_share.views_controller.appLoader.controllers;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.controllers.UserInformationKeeper;
import ar.uba.fi.tdp.wifi_share.model.InvalidUserException;
import ar.uba.fi.tdp.wifi_share.model.User;
import ar.uba.fi.tdp.wifi_share.model.UserInformationPersistence;
import ar.uba.fi.tdp.wifi_share.model.UsersDatabase;
import ar.uba.fi.tdp.wifi_share.views_controller.SessionAuthenticator;

public class LoginButton implements View.OnClickListener {

    private EditText user;
    private EditText password;

    private Button loginButton;
    private UserInformationPersistence userInformationPersistence;
    private SessionAuthenticator sesionAuthenticator;
    private Activity activity;

    public LoginButton(Activity activity) {
        this.loginButton = (Button) activity.findViewById(R.id.login_button_startup);
        this.loginButton.setOnClickListener(this);

        user = (EditText) activity.findViewById(R.id.user_id_startup);
        password = (EditText) activity.findViewById(R.id.password_id_startup);

        userInformationPersistence = new UserInformationPersistence(activity);
        sesionAuthenticator = new SessionAuthenticator(activity);
        this.activity = activity;
    }

    public void onClick(View v) {

        UsersDatabase usersDatabase = new UsersDatabase();
        String userID = user.getText().toString();
        String userPassword = password.getText().toString();
        User login = new User(userID, userPassword);

        try {
            if (usersDatabase.isValidUser(login) == true) {
                userInformationPersistence.persistAuthenticationInformation(userID, userPassword);
                UserInformationKeeper.accessUserInformationKeeper(activity).updateUserInformation(activity);
                sesionAuthenticator.loginSuccesfull(v.getContext());
            }
        }

        catch ( InvalidUserException iue) {
            // display invalid user notification!
        }
    }

    public void setButtonEnabledState(boolean state) {
        loginButton.setEnabled(state);
    }
}
