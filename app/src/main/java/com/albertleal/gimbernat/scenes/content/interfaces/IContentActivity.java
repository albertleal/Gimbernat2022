package com.albertleal.gimbernat.scenes.content.interfaces;

import android.content.Context;

import com.albertleal.gimbernat.model.CapsuleModel;

import java.util.ArrayList;

public interface IContentActivity {
    void showLoading();

    void hideLoading();

    void showError(String error);

    void showEmptyContent();

    void showContent(ArrayList<CapsuleModel> items);

    void showNewContent(ArrayList<CapsuleModel> items);

    Context getContext();
}
