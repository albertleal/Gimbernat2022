package com.albertleal.gimbernat.scenes.welcome.interfaces;

public interface IWelcomeActivity {
    void showSpinner();
    void hideSpinner();
    void showError(String error);
    void navigateToPrivate();
}
