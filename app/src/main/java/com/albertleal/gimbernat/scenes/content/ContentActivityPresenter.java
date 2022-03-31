package com.albertleal.gimbernat.scenes.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.dasources.MyCollectionDataSource;
import com.albertleal.gimbernat.dasources.SessionDataSource;
import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.model.ItemModel;
import com.albertleal.gimbernat.scenes.content.interfaces.IContentActivity;
import com.albertleal.gimbernat.scenes.content.interfaces.IContentActivityPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContentActivityPresenter extends BaseAdapter implements IContentActivityPresenter {
    private ContentActivity view;

    public ContentActivityPresenter(ContentActivity view){
        this.view = view;
    }

    private Boolean isFirstTime = true;
    private ArrayList<ItemModel> itemList = new ArrayList<ItemModel>();

    @Override
    public void fetchContent() {
        this.view.showLoading();

        MyCollectionDataSource.shared.fetch(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                ArrayList<ItemModel> itemList = (ArrayList<ItemModel>) responseObject;
                ContentActivityPresenter.this.itemList = itemList;

                if(itemList.isEmpty()){
                    ContentActivityPresenter.this.view.showEmptyContent();
                }else{
                    if(isFirstTime){
                        ContentActivityPresenter.this.view.showContent(itemList);
                    }else {
                        ContentActivityPresenter.this.view.showNewContent(itemList);
                    }

                }

                isFirstTime = false;

                ContentActivityPresenter.this.view.hideLoading();
            }

            @Override
            public void onError(String error) {
                ContentActivityPresenter.this.view.showError(error);
                ContentActivityPresenter.this.view.hideLoading();
            }
        });




    }

    @Override
    public int getCount() {
        return this.itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View theView, ViewGroup viewGroup) {
        if (theView == null) {
            LayoutInflater inflater = (LayoutInflater) ContentActivityPresenter.this.view.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Dedicated layout for the item itself
            theView = inflater.inflate(R.layout.content_row_item, viewGroup, false);
        }

        final ItemModel itemModel = (ItemModel) getItem(i);

        //Setting the texts
        ((TextView) theView.findViewById(R.id.rowItemID)).setText(itemModel.id);
        ((TextView) theView.findViewById(R.id.rowItemDescription)).setText(itemModel.description);
        ((TextView) theView.findViewById(R.id.rowItemName)).setText(itemModel.name);


        Picasso.get().load(itemModel.url).into((ImageView) theView.findViewById(R.id.contentRowItemImage));


        return theView;

    }
}
