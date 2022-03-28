package com.albertleal.gimbernat.scenes.main;

import com.albertleal.gimbernat.dasources.SessionDataSource;
import com.albertleal.gimbernat.scenes.main.interfaces.IMainActivity;
import com.albertleal.gimbernat.scenes.main.interfaces.IMainActivityPresenter;

public class MainActivityPresenter  implements IMainActivityPresenter {
    //MVP Variables
    private IMainActivity view;

    public MainActivityPresenter(IMainActivity view){
        this.view = view;
    }

    @Override
    public void bootApplication() {
        view.showSpinner();
        if(SessionDataSource.shared.isUserLogedIn()){
            view.hideSpinner();
            view.navigateToPrivate();
        } else{
            view.hideSpinner();
            view.navigateToPublic();
        }
    }
}
