package com.albertleal.gimbernat.dasources;

import androidx.annotation.NonNull;

import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.model.ItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyCollectionDataSource {
    public static MyCollectionDataSource shared = new MyCollectionDataSource();

    private ArrayList<ItemModel> itemList = new ArrayList<ItemModel>();

    public void fetch(final Callback callback){

        final DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("myCollectionName");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<ItemModel> itemList = new ArrayList<ItemModel>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {
                    itemList.add(new ItemModel(item_snapshot));
                }

                MyCollectionDataSource.this.itemList = itemList;
                callback.onSuccess(itemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError("Error downloading collection MyCollectionName: "+databaseError.getMessage());
            }
        };


        databaseReference.addValueEventListener(eventListener);
    }
}
