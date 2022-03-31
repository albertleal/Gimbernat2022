package com.albertleal.gimbernat.model;

import com.google.firebase.database.DataSnapshot;

public class ItemModel {
    public String id;
    public String url;
    public String description;
    public String name;

    public ItemModel(DataSnapshot item_snapshot) {
        this.id = item_snapshot.getKey().toString();

        this.description = item_snapshot.child("description").exists()
                ? item_snapshot.child("description").getValue().toString()
                : "";

        this.url = item_snapshot.child("url").exists()
                ? item_snapshot.child("url").getValue().toString()
                : "";

        this.name = item_snapshot.child("name").exists()
                ? item_snapshot.child("name").getValue().toString()
                : id;

    }
}
