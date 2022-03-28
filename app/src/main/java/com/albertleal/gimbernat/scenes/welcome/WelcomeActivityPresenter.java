package com.albertleal.gimbernat.scenes.welcome;

import com.albertleal.gimbernat.dasources.SessionDataSource;
import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.scenes.main.interfaces.IMainActivity;
import com.albertleal.gimbernat.scenes.welcome.interfaces.IWelcomeActivity;
import com.albertleal.gimbernat.scenes.welcome.interfaces.IWelcomeActivityPresenter;

public class WelcomeActivityPresenter implements IWelcomeActivityPresenter {
    //MVP Variables
    private IWelcomeActivity view;

    public WelcomeActivityPresenter(IWelcomeActivity view){
        this.view = view;
    }
    @Override
    public void login() {
        WelcomeActivityPresenter.this.view.showSpinner();
        SessionDataSource.shared.signInAnonymously(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                WelcomeActivityPresenter.this.view.hideSpinner();
                WelcomeActivityPresenter.this.view.navigateToPrivate();
            }

            @Override
            public void onError(String error) {
                WelcomeActivityPresenter.this.view.hideSpinner();
                WelcomeActivityPresenter.this.view.showError(error);
            }
        });
    }
}
