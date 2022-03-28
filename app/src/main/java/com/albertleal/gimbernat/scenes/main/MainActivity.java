package com.albertleal.gimbernat.scenes.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.Intent;
import android.os.Bundle;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.dasources.SessionDataSource;
import com.albertleal.gimbernat.scenes.content.ContentActivity;
import com.albertleal.gimbernat.scenes.main.interfaces.IMainActivity;
import com.albertleal.gimbernat.scenes.welcome.WelcomeActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new MainActivityPresenter(this);
        this.presenter.bootApplication();
    }

    @Override
    public void navigateToPublic() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public void navigateToPrivate() {
        Intent intent = new Intent(MainActivity.this, ContentActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }
}