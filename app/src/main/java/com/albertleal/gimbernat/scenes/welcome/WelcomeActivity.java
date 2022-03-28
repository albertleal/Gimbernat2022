package com.albertleal.gimbernat.scenes.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.scenes.content.ContentActivity;
import com.albertleal.gimbernat.scenes.main.MainActivity;
import com.albertleal.gimbernat.scenes.main.MainActivityPresenter;
import com.albertleal.gimbernat.scenes.welcome.interfaces.IWelcomeActivity;

public class WelcomeActivity extends AppCompatActivity  implements IWelcomeActivity {
    private WelcomeActivityPresenter presenter;

    //UI
    private ProgressBar spinner;
    private Button loginButton;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //SETUP PATTERN VARIABLES
        this.presenter = new WelcomeActivityPresenter(this);

        //SETUP UI
        this.spinner = (ProgressBar) this.findViewById(R.id.welcomeProgressBar);
        this.loginButton = (Button) this.findViewById(R.id.welcomeLoginButton);
        this.errorText =  (TextView) this.findViewById(R.id.welcomeErrorTextView);

        //SETUP EVENT LISTENERS
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WelcomeActivity.this.presenter.login();
            }
        });
    }

    @Override
    public void showSpinner() {
        WelcomeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WelcomeActivity.this.spinner.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideSpinner() {
        WelcomeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WelcomeActivity.this.spinner.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showError(String error) {
        WelcomeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WelcomeActivity.this.errorText.setText(error);
                WelcomeActivity.this.errorText.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void navigateToPrivate() {
        Intent intent = new Intent(WelcomeActivity.this, ContentActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        WelcomeActivity.this.startActivity(intent);
    }
}