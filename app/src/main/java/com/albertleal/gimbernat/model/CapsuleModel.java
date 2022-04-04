package com.albertleal.gimbernat.model;

import com.google.firebase.database.DataSnapshot;

public class CapsuleModel {
    public String id;
    public String icon;
    public String description;
    public String name;

    public CapsuleModel(DataSnapshot item_snapshot) {
        this.id = item_snapshot.getKey().toString();

        this.description = item_snapshot.child("description").exists()
                ? item_snapshot.child("description").getValue().toString()
                : "";

        this.icon = item_snapshot.child("icon").exists()
                ? item_snapshot.child("icon").getValue().toString()
                : "https://firebasestorage.googleapis.com/v0/b/gimbernat-bb726.appspot.com/o/capsules%2Fclassic%2Fempty.jpeg?alt=media&token=c58a5e19-bbf7-46f3-90a5-137f1ea07216";

        this.name = item_snapshot.child("name").exists()
                ? item_snapshot.child("name").getValue().toString()
                : id;

    }
}
