package com.albertleal.gimbernat.scenes.content;

import androidx.appcompat.app.AppCompatActivity;
import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.model.ItemModel;
import com.albertleal.gimbernat.scenes.content.interfaces.IContentActivity;
import com.albertleal.gimbernat.scenes.main.MainActivityPresenter;
import com.albertleal.gimbernat.scenes.main.interfaces.IMainActivity;
import com.albertleal.gimbernat.scenes.welcome.WelcomeActivity;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity implements IContentActivity {
    private ContentActivityPresenter presenter;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        this.spinner = (ProgressBar) this.findViewById(R.id.contentProgressBar);

        this.presenter = new ContentActivityPresenter(this);
        this.presenter.fetchContent();
    }

    @Override
    public void showLoading() {
        ContentActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ContentActivity.this.spinner.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideLoading() {
        ContentActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ContentActivity.this.spinner.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showError(String error) {
        Log.i("CONTENT_ACTIVITY_ERROR", error);
    }

    @Override
    public void showEmptyContent() {

    }

    @Override
    public void showContent(ArrayList<ItemModel> items) {
        ContentActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((GridView) ContentActivity.this.findViewById(R.id.contentGrid))
                        .setAdapter(ContentActivity.this.presenter);
            }
        });

        Log.i("CONTENT_ACTIVITY", ""+items.size());

    }

    @Override
    public void showNewContent(ArrayList<ItemModel> items) {
        Log.i("CONTENT_ACTIVITY_NEW_CONTENT", ""+items.size());
        ((GridView) findViewById(R.id.contentGrid)).setAdapter(this.presenter);
    }

}