package com.albertleal.gimbernat.scenes.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.dasources.CapsulesDataSource;
import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.model.CapsuleModel;
import com.albertleal.gimbernat.scenes.content.interfaces.IContentActivity;
import com.albertleal.gimbernat.scenes.content.interfaces.IContentActivityPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContentActivityPresenter extends BaseAdapter implements IContentActivityPresenter {
    private final IContentActivity view;

    public ContentActivityPresenter(ContentActivity view){
        this.view = view;
    }

    private Boolean isFirstTime = true;
    private ArrayList<CapsuleModel> itemList = new ArrayList<CapsuleModel>();

    @Override
    public void fetchContent() {
        ContentActivityPresenter.this.view.showLoading();

        CapsulesDataSource.shared.fetch(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                ArrayList<CapsuleModel> itemList = (ArrayList<CapsuleModel>) responseObject;
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
            LayoutInflater inflater = (LayoutInflater) ContentActivityPresenter.this.view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Dedicated layout for the item itself
            theView = inflater.inflate(R.layout.content_row_item, viewGroup, false);
        }

        final CapsuleModel capsuleModel = (CapsuleModel) getItem(i);

        //Setting the texts
        ((TextView) theView.findViewById(R.id.rowItemID)).setText(capsuleModel.id);
        ((TextView) theView.findViewById(R.id.rowItemDescription)).setText(capsuleModel.description);
        ((TextView) theView.findViewById(R.id.rowItemName)).setText(capsuleModel.name);
        ((TextView) theView.findViewById(R.id.rowItemCategory)).setText("ISPIRAZIONE ITALIANA");


        Picasso.get().load(capsuleModel.icon).into((ImageView) theView.findViewById(R.id.contentRowItemImage));


        return theView;

    }
}
