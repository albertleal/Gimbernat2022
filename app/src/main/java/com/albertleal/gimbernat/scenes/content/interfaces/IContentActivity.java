package com.albertleal.gimbernat.scenes.content.interfaces;

import com.albertleal.gimbernat.model.ItemModel;

import java.util.ArrayList;

public interface IContentActivity {
    void showLoading();

    void hideLoading();

    void showError(String error);

    void showEmptyContent();

    void showContent(ArrayList<ItemModel> items);

    void showNewContent(ArrayList<ItemModel> items);
}
