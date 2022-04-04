package com.albertleal.gimbernat.dasources;

import androidx.annotation.NonNull;

import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.model.CapsuleModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CapsulesDataSource {
    public static CapsulesDataSource shared = new CapsulesDataSource();

    private ArrayList<CapsuleModel> capsulesList = new ArrayList<CapsuleModel>();

    public void fetch(final Callback callback){

        final DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("Capsules");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<CapsuleModel> capsulesList = new ArrayList<CapsuleModel>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {
                    capsulesList.add(new CapsuleModel(item_snapshot));
                }

                CapsulesDataSource.this.capsulesList = capsulesList;
                callback.onSuccess(capsulesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError("Error downloading collection MyCollectionName: "+databaseError.getMessage());
            }
        };


        databaseReference.addValueEventListener(eventListener);
    }
}
