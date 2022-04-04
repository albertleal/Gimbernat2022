package com.albertleal.gimbernat.scenes.content;

import androidx.appcompat.app.AppCompatActivity;
import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.model.CapsuleModel;
import com.albertleal.gimbernat.scenes.content.interfaces.IContentActivity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    public void showContent(ArrayList<CapsuleModel> items) {
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
    public void showNewContent(ArrayList<CapsuleModel> items) {
        Log.i("CONTENT_ACTIVITY_NEW_CONTENT", ""+items.size());
        ((GridView) findViewById(R.id.contentGrid)).setAdapter(this.presenter);
    }

    @Override
    public Context getContext() {
        return ContentActivity.this;
    }


}